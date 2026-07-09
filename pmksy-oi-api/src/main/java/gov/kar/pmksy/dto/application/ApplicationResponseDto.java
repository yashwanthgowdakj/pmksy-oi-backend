package gov.kar.pmksy.dto.application;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicationResponseDto {

    private Long applicationId;

    private String applicationNumber;

    private Long farmerId;

    private String farmerName;

    private LocalDate applicationDate;

    private String financialYear;

    private String applicationStatus;

    private String currentLevel;

    private String talukName;

    private String districtName;

    private String remarks;

    private Boolean activeFlag;
}