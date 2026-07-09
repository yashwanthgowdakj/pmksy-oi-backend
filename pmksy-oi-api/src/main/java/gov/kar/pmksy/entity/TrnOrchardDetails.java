package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trn_orchard_details")
public class TrnOrchardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orchard_id")
    private Long orchardId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private TrnFarmerApplication application;

    @Column(name = "crop_name")
    private String cropName;

    @Column(name = "variety_name")
    private String varietyName;

    @Column(name = "area_acre")
    private BigDecimal areaAcre;

    @Column(name = "plant_count")
    private Integer plantCount;

    @Column(name = "irrigation_type")
    private String irrigationType;

    @Column(name = "spacing")
    private String spacing;

    @Column(name = "orchard_status")
    private String orchardStatus;

    @Column(name = "survey_number")
    private String surveyNumber;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "active_flag")
    private Boolean activeFlag;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;
}