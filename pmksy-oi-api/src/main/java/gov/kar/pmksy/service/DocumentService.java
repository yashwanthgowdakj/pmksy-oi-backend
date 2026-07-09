package gov.kar.pmksy.service;

import gov.kar.pmksy.dto.document.DocumentRequestDto;
import gov.kar.pmksy.dto.document.DocumentResponseDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DocumentService {

    DocumentResponseDto uploadDocument(
            DocumentRequestDto request);

    List<DocumentResponseDto>
    getDocumentsByApplication(
            Long applicationId);

    ResponseEntity<Resource>
    downloadDocument(
            Long documentId);
}