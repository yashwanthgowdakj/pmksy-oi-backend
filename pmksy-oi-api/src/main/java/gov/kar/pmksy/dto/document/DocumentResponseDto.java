package gov.kar.pmksy.dto.document;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentResponseDto {

    private Long documentId;

    private Long applicationId;

    private String documentType;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private Boolean activeFlag;

    private LocalDateTime uploadedDate;

    private Boolean talukVerified;

    private String talukVerifiedByName;

    private LocalDateTime talukVerifiedDate;

    private String talukRemarks;

    private Boolean districtVerified;

    private String districtVerifiedByName;

    private LocalDateTime districtVerifiedDate;

    private String districtRemarks;
}