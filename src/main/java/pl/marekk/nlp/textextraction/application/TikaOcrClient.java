package pl.marekk.nlp.textextraction.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.HttpStatus;
import pl.marekk.nlp.textextraction.application.headers.TikaHeaders;
import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;
import pl.marekk.nlp.textextraction.domain.TextExtractionResponseFactory;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
class TikaOcrClient implements Function<TextExtractionCommand, TextExtractionResponseFactory> {
  static final okhttp3.logging.HttpLoggingInterceptor.Logger httpLogger = log::info;
  private final OkHttpClient httpClient;
  private final String tikaUrl;
  private final Map<String, String> headers;

  @Override
  public TextExtractionResponseFactory apply(TextExtractionCommand textExtractionCommand) {
    Request request = buildRequest(textExtractionCommand);
    Response response = callHttp(request);
    return TikaTextExtractionResponseFactory.of(response);
  }

  private Response callHttp(Request request) {
    try {
      return httpClient.newCall(request).execute();
    } catch (IOException e) {
      log.warn("error during call tika server {}", e.getMessage());
      return new Response.Builder()
          .request(request)
          .message(e.getMessage())
          .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
          .protocol(Protocol.HTTP_1_1)
          .body(ResponseBody.create(e.getMessage(), MediaType.parse("application/json")))
          .build();
    }
  }

  private Request buildRequest(TextExtractionCommand textExtractionCommand) {
    log.info("building the tika request from: {}", textExtractionCommand);
    Request.Builder requestBuilder =
        new Request.Builder()
                .url(tikaUrl)
                .put(RequestBody.create(textExtractionCommand.getContent()));
    Map<String, String> tikaHeaders = TikaHeaders.toTikaHeaders(textExtractionCommand);
    if (tikaHeaders != null && !tikaHeaders.isEmpty()) {
      requestBuilder.headers(Headers.of(tikaHeaders));
    }
    return requestBuilder.build();
  }
}
