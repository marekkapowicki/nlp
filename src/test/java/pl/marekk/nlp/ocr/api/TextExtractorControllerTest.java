package pl.marekk.nlp.ocr.api;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Test;
import pl.marekk.nlp.nlp.domain.TestTextExtractor;

import java.io.File;
import java.io.IOException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

public class TextExtractorControllerTest {

  @Test
  public void processingTheSuccessFlow() throws IOException {

    //given
    final OcrController ocrController = new OcrController(TestTextExtractor.success("ocr is cool"));
    RestAssuredMockMvc.standaloneSetup(ocrController);

    // expect
    given()
        .multiPart("fileToOcr", sampleFile())
        .contentType("multipart/form-data")
        .when()
        .post("/api/actions/ocr")
    .then()
        .log()
        .ifValidationFails()
        .statusCode(OK.value())
        .body("text", equalTo("ocr is cool"));
  }

  @Test
  public void processingTheFailureFlow() throws IOException {

    //given
    final OcrController ocrController = new OcrController(TestTextExtractor.failure(503, "mock error"));
    RestAssuredMockMvc.standaloneSetup(ocrController);
    // expect
    given()
        .multiPart("fileToOcr", sampleFile())
        .contentType("multipart/form-data")
    .when()
        .post("/api/actions/ocr")
    .then()
        .log()
        .ifValidationFails()
        .statusCode(SERVICE_UNAVAILABLE.value())
        .body("message", equalTo("mock error"));
  }

  private static File sampleFile() throws IOException {
    File tempFile = File.createTempFile("sample", "pdf");
    tempFile.deleteOnExit();
    return tempFile;
  }
}
