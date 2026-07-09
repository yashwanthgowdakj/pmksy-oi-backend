package gov.kar.pmksy.controller;

import gov.kar.pmksy.dto.document.DocumentRequestDto;
import gov.kar.pmksy.dto.document.DocumentResponseDto;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService documentService;
@GetMapping("/{documentId}/download")
public ResponseEntity<Resource> downloadDocument(
        @PathVariable Long documentId) {

    return documentService.downloadDocument(
            documentId);
}
    @PostMapping("/upload")
    public ApiResponse<DocumentResponseDto> uploadFile(
            @RequestParam("applicationId") Long applicationId,
            @RequestParam("documentType") String documentType,
            @RequestParam("file") MultipartFile file)
            throws Exception {

        System.out.println("==================================");
        System.out.println("DOCUMENT UPLOAD STARTED");
        System.out.println("Application ID : " + applicationId);
        System.out.println("Document Type  : " + documentType);
        System.out.println("File Name      : " + file.getOriginalFilename());
        System.out.println("File Size      : " + file.getSize());
        System.out.println("==================================");

        /*
         * READ FILE CONTENT FIRST
         * BEFORE transferTo()
         */
        byte[] fileContent = file.getBytes();

        String uploadDirectory =
                System.getProperty("user.dir")
                        + File.separator
                        + "uploads"
                        + File.separator
                        + applicationId
                        + File.separator;

        File directory = new File(uploadDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destinationFile =
                new File(
                        uploadDirectory
                                + file.getOriginalFilename());

        file.transferTo(destinationFile);

        System.out.println(
                "FILE SAVED TO DISK : "
                        + destinationFile.getAbsolutePath());

        DocumentRequestDto request =
                new DocumentRequestDto();

        request.setApplicationId(applicationId);

        request.setDocumentType(documentType);

        request.setFileName(
                file.getOriginalFilename());

        request.setFilePath(
                destinationFile.getAbsolutePath());

        request.setUploadedBy(1L);

        request.setFileSize(
                file.getSize());

        request.setContentType(
                file.getContentType());

        request.setFileContent(
                fileContent);

        System.out.println(
                "CALLING DOCUMENT SERVICE");

        DocumentResponseDto response =
                documentService.uploadDocument(
                        request);

        System.out.println(
                "DOCUMENT SAVED TO DATABASE");

        return ApiResponse
                .<DocumentResponseDto>builder()
                .success(true)
                .message(
                        "Document uploaded successfully")
                .data(response)
                .build();
    }

    @GetMapping("/application/{applicationId}")
    public ApiResponse<List<DocumentResponseDto>>
    getDocumentsByApplication(
            @PathVariable Long applicationId) {

        return ApiResponse
                .<List<DocumentResponseDto>>builder()
                .success(true)
                .message(
                        "Documents fetched successfully")
                .data(
                        documentService
                                .getDocumentsByApplication(
                                        applicationId))
                .build();
    }
}