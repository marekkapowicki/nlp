package pl.marekk.nlp.textextraction.application;

import io.vavr.API;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pl.marekk.nlp.textextraction.domain.TextExtractionResponseFactory;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
class TikaTextExtractionResponseFactory implements TextExtractionResponseFactory {
  private final Response httpResponse;

  static TikaTextExtractionResponseFactory of(Response httpResponse) {
    log.info("response {}", httpResponse);
    return new TikaTextExtractionResponseFactory(httpResponse);
  }

  @Override
  public boolean isSuccessful() {
    return httpResponse.isSuccessful();
  }

  @Override
  public int code() {
    return httpResponse.code();
  }

  @Override
  public String message() {
    return httpResponse.message();
  }

  @Override
  public String bodyAsString() {
    return Optional.ofNullable(httpResponse.body())
        .map(API.unchecked(ResponseBody::string))
        .orElseThrow(() -> new IllegalStateException("tika response body is null"));
  }

  @Override
  public void closeStream() {
    httpResponse.close();
  }
}
