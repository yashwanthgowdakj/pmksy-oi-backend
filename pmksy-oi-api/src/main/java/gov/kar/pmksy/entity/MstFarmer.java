package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "mst_farmer")
public class MstFarmer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "farmer_id")
    private Long farmerId;

    @Column(name = "fid_number", nullable = false, unique = true)
    private String fidNumber;

    @Column(name = "farmer_name", nullable = false)
    private String farmerName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "aadhaar_masked")
    private String aadhaarMasked;

    @Column(name = "total_land_extent")
    private BigDecimal totalLandExtent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private MstGender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_category_id")
    private MstSocialCategory socialCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private MstAdministrativeDistrict district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taluk_id")
    private MstAdministrativeTaluk taluk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobli_id")
    private MstAdministrativeHobli hobli;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "village_id")
    private MstAdministrativeVillage village;
}