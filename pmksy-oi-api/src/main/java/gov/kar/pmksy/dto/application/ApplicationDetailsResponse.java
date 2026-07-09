package gov.kar.pmksy.dto.application;

import gov.kar.pmksy.dto.document.DocumentResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ApplicationDetailsResponse {

    private Long applicationId;

    private String applicationNumber;

    private String status;

    private Long farmerId;

    private String farmerName;

    private String mobileNumber;

    private List<DocumentResponseDto> documents;
}