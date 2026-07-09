package gov.kar.pmksy.dto.orchard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrchardDetailsResponseDto {

    private Long orchardId;

    private Long applicationId;

    private String cropName;

    private String varietyName;

    private BigDecimal areaAcre;

    private Integer plantCount;

    private String irrigationType;

    private String spacing;

    private String orchardStatus;

    private String surveyNumber;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Boolean activeFlag;
}