package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.dto.document.DocumentRequestDto;
import gov.kar.pmksy.dto.document.DocumentResponseDto;
import gov.kar.pmksy.entity.TrnApplicationDocument;
import gov.kar.pmksy.entity.TrnFarmerApplication;
import gov.kar.pmksy.repository.ApplicationRepository;
import gov.kar.pmksy.repository.DocumentRepository;
import gov.kar.pmksy.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl
        implements DocumentService {

    private final DocumentRepository documentRepository;

    private final ApplicationRepository applicationRepository;

    @Override
    public DocumentResponseDto uploadDocument(
            DocumentRequestDto request) {

        try {

            System.out.println(
                    "DOCUMENT SAVE STARTED");

            TrnFarmerApplication application =
                    applicationRepository.findById(
                                    request.getApplicationId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Application not found"));

            TrnApplicationDocument document =
                    new TrnApplicationDocument();

            document.setApplication(
                    application);

            document.setDocumentType(
                    request.getDocumentType());

            document.setFileName(
                    request.getFileName());

            document.setFilePath(
                    request.getFilePath());

            document.setFileSize(
                    request.getFileSize());

            document.setContentType(
                    request.getContentType());

            document.setFileContent(
                    request.getFileContent());

            document.setUploadedBy(
                    request.getUploadedBy());

            document.setUploadedDate(
                    LocalDateTime.now());

            document.setActiveFlag(true);

            document =
                    documentRepository.save(
                            document);

            System.out.println(
                    "DOCUMENT SAVED");

            return mapToResponse(document);

        } catch (Exception ex) {

            ex.printStackTrace();

            throw ex;
        }
    }

    @Override
    public ResponseEntity<Resource> downloadDocument(
            Long documentId) {

        try {

            TrnApplicationDocument document =
                    documentRepository.findById(documentId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Document not found"));

            System.out.println(
                    "===================================");

            System.out.println(
                    "DOCUMENT DOWNLOAD STARTED");

            System.out.println(
                    "Document Id : "
                            + document.getDocumentId());

            System.out.println(
                    "File Name : "
                            + document.getFileName());

            System.out.println(
                    "File Path : "
                            + document.getFilePath());

            System.out.println(
                    "Content Type : "
                            + document.getContentType());

            System.out.println(
                    "File Content Null : "
                            + (document.getFileContent() == null));

            System.out.println(
                    "===================================");

            Resource resource;

            /*
             * DOWNLOAD FROM DATABASE
             */
            if (document.getFileContent() != null
                    && document.getFileContent().length > 0) {

                System.out.println(
                        "DOWNLOADING FROM DATABASE");

                resource =
                        new ByteArrayResource(
                                document.getFileContent());

            } else {

                /*
                 * DOWNLOAD FROM FILE SYSTEM
                 */
                System.out.println(
                        "DOWNLOADING FROM FILE SYSTEM");

                Path path =
                        Paths.get(
                                document.getFilePath());

                resource =
                        new UrlResource(
                                path.toUri());

                if (!resource.exists()) {

                    throw new RuntimeException(
                            "File not found : "
                                    + document.getFilePath());
                }
            }

            MediaType mediaType;

            try {

                mediaType =
                        MediaType.parseMediaType(
                                document.getContentType());

            } catch (Exception ex) {

                mediaType =
                        MediaType.APPLICATION_OCTET_STREAM;
            }

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\""
                                    + document.getFileName()
                                    + "\"")
                    .body(resource);

        } catch (Exception ex) {

            ex.printStackTrace();

            throw new RuntimeException(
                    "Error downloading document",
                    ex);
        }
    }

    @Override
    public List<DocumentResponseDto>
    getDocumentsByApplication(
            Long applicationId) {

        return documentRepository
                .findByApplicationApplicationId(
                        applicationId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private DocumentResponseDto mapToResponse(
            TrnApplicationDocument document) {

        DocumentResponseDto dto =
                new DocumentResponseDto();

        dto.setDocumentId(
                document.getDocumentId());

        dto.setApplicationId(
                document.getApplication()
                        .getApplicationId());

        dto.setDocumentType(
                document.getDocumentType());

        dto.setFileName(
                document.getFileName());

        dto.setFilePath(
                document.getFilePath());

        dto.setFileSize(
                document.getFileSize());

        dto.setActiveFlag(
                document.getActiveFlag());

        return dto;
    }
}