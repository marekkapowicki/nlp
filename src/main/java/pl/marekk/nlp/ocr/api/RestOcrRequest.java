package pl.marekk.nlp.ocr.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.marekk.nlp.ocr.domain.OcrCommand;
import pl.marekk.nlp.ocr.domain.OcrCommandBuilder;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Data
public class RestOcrRequest {
    @NotNull(message = "file to upload is null")
    private MultipartFile fileToOcr;

    public static RestOcrRequest valueOf(MultipartFile fileToOcr) {
        return new RestOcrRequest(fileToOcr);
    }

    Optional<OcrCommand> buildOcrCommand() {
        return OcrCommandBuilder.buildOcrCommand(this::contentAsByteArray, fileToOcr.getContentType(), fileToOcr.getOriginalFilename());
    }

    private byte[] contentAsByteArray() {
        try {
            return StreamUtils.copyToByteArray(fileToOcr.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String toString() {
        return "RestOcrRequest{" +
                "fileToOcr=" + fileToOcr.getOriginalFilename() +
                '}';
    }
}
