package pl.marekk.nlp.ocr.application;

import io.vavr.API;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import pl.marekk.nlp.ocr.domain.OcrResponseFactory;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class TikaOcrResponseFactory implements OcrResponseFactory {
  private final Response httpResponse;

  static TikaOcrResponseFactory of(Response httpResponse) {
    return new TikaOcrResponseFactory(httpResponse);
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
