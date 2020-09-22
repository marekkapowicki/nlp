package pl.marekk.nlp.ocr.application;

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
import pl.marekk.nlp.ocr.domain.OcrCommand;
import pl.marekk.nlp.ocr.domain.OcrResponseFactory;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

import static pl.marekk.nlp.ocr.application.TikaHeaders.tikaHeaders;

@Slf4j
@RequiredArgsConstructor
class TikaOcrClient implements Function<OcrCommand, OcrResponseFactory> {
  static final okhttp3.logging.HttpLoggingInterceptor.Logger httpLogger = log::info;
  private final OkHttpClient httpClient;
  private final String tikaUrl;
  private final Map<String, String> headers;

  @Override
  public OcrResponseFactory apply(OcrCommand ocrCommand) {
    Request request = buildRequest(ocrCommand);
    Response response = callHttp(request);
    return TikaOcrResponseFactory.of(response);
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

  private Request buildRequest(OcrCommand ocrCommand) {
    log.info("building the tika request from: {}", ocrCommand);
    Request.Builder requestBuilder =
        new Request.Builder().url(tikaUrl).put(RequestBody.create(ocrCommand.getContent()));
    Map<String, String> tikaHeaders = tikaHeaders(ocrCommand, headers);
    if (tikaHeaders != null && !tikaHeaders.isEmpty()) {
      requestBuilder.headers(Headers.of(tikaHeaders));
    }
    return requestBuilder.build();
  }
}
