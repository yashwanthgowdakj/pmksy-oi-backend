package gov.kar.pmksy.dto.application;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApplicationRequestDto {

    @NotNull
    private Long farmerId;

    @NotNull
    private String financialYear;

    private String remarks;

    private String surveyNumber;

    private String villageName;

    private BigDecimal extentAcre;

    private String interventionType;

    private BigDecimal estimatedCost;
}