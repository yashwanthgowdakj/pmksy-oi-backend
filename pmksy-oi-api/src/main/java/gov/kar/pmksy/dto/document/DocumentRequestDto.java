package gov.kar.pmksy.dto.document;

import lombok.Data;

@Data
public class DocumentRequestDto {

    private Long applicationId;

    private String documentType;

    private String fileName;

    private String filePath;

    private Long uploadedBy;

    private Long fileSize;

    private String contentType;

    private byte[] fileContent;
}