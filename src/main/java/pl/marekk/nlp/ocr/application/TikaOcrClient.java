package pl.marekk.nlp.ocr.application;

import lombok.AllArgsConstructor;
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

@Slf4j
@AllArgsConstructor
class TikaOcrClient implements Function<OcrCommand, OcrResponseFactory> {
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
        log.info("building the tika request: {}", ocrCommand.getContentType());
        Request.Builder requestBuilder = new Request.Builder()
                .url(tikaUrl)
                .put(RequestBody.create(ocrCommand.getContent()));
        if (headers != null && !headers.isEmpty()) {
            requestBuilder
                    .headers(Headers.of(headers));
        }
        return requestBuilder.build();
    }


}
