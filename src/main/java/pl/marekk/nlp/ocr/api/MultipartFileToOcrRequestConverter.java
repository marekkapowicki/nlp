package pl.marekk.nlp.ocr.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

class MultipartFileToOcrRequestConverter implements Converter<MultipartFile, RestOcrRequest> {
    @Override
    public RestOcrRequest convert(MultipartFile multipartFile) {
        return RestOcrRequest.valueOf(multipartFile);
    }
}
