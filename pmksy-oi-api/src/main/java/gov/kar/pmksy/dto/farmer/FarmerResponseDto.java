package gov.kar.pmksy.dto.farmer;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FarmerResponseDto {

    private Long farmerId;

    private String fidNumber;

    private String farmerName;

    private String fatherName;

    private String mobileNumber;

    private String aadhaarMasked;

    private String gender;

    private String socialCategory;

    private String district;

    private String taluk;

    private String hobli;

    private String village;

    private BigDecimal totalLandExtent;

    private Boolean activeFlag;
}