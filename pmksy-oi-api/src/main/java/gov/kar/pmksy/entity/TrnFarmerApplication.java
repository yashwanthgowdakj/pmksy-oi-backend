package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trn_farmer_application")
public class TrnFarmerApplication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(
            name = "application_number",
            nullable = false,
            unique = true
    )
    private String applicationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmer_id")
    private MstFarmer farmer;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "financial_year")
    private String financialYear;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "current_level")
    private String currentLevel;

    @Column(name = "taluk_name")
    private String talukName;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "taluk_verified_by")
    private Long talukVerifiedBy;

    @Column(name = "taluk_verified_date")
    private LocalDateTime talukVerifiedDate;

    @Column(name = "district_verified_by")
    private Long districtVerifiedBy;

    @Column(name = "district_verified_date")
    private LocalDateTime districtVerifiedDate;
    
}