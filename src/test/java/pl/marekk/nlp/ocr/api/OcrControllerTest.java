package pl.marekk.nlp.ocr.api;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import pl.marekk.nlp.nlp.domain.TestOcr;

import java.io.File;
import java.io.IOException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

public class OcrControllerTest {

  private final OcrController ocrController = new OcrController(TestOcr.success("ocr is cool"));

  @Before
  public void initialiseRestAssuredMockMvcStandalone() {
    RestAssuredMockMvc.standaloneSetup(ocrController);
  }

  @Test
  public void processingTheSuccessFlow() throws IOException {

    //given
    File tempFile = File.createTempFile("test", "pdf");
    tempFile.deleteOnExit();
    // expect
    given()
        .multiPart("fileToOcr", tempFile)
        .contentType("multipart/form-data")
        .when()
        .post("/api/actions/ocr")
    .then()
        .log()
        .ifValidationFails()
        .statusCode(OK.value())
        .body("text", equalTo("ocr is cool"));
  }
}
