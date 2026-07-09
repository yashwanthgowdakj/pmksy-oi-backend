package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "trn_application_details")
public class TrnApplicationDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_detail_id")
    private Long applicationDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private TrnFarmerApplication application;

    @Column(name = "survey_number")
    private String surveyNumber;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "extent_acre")
    private BigDecimal extentAcre;

    @Column(name = "intervention_type")
    private String interventionType;

    @Column(name = "estimated_cost")
    private BigDecimal estimatedCost;
}