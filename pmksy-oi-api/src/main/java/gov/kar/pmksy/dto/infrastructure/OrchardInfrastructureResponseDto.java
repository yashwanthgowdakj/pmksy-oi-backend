package gov.kar.pmksy.dto.infrastructure;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrchardInfrastructureResponseDto {

    private Long infrastructureId;

    private Long orchardId;

    private String infrastructureType;

    private String unit;

    private BigDecimal quantity;

    private BigDecimal estimatedCost;

    private BigDecimal subsidyAmount;

    private BigDecimal farmerContribution;

    private String status;

    private Boolean activeFlag;
}