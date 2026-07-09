package gov.kar.pmksy.dto.farmer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FarmerRequestDto {

    @NotBlank
    private String fidNumber;

    @NotBlank
    private String farmerName;

    private String fatherName;

    private String mobileNumber;

    private String aadhaarMasked;

    @NotNull
    private Long genderId;

    @NotNull
    private Long socialCategoryId;

    @NotNull
    private Long districtId;

    @NotNull
    private Long talukId;

    @NotNull
    private Long hobliId;

    @NotNull
    private Long villageId;

    private BigDecimal totalLandExtent;
}