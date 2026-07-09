package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trn_orchard_infrastructure")
public class TrnOrchardInfrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "infrastructure_id")
    private Long infrastructureId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orchard_id")
    private TrnOrchardDetails orchard;

    @Column(name = "infrastructure_type")
    private String infrastructureType;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "estimated_cost")
    private BigDecimal estimatedCost;

    @Column(name = "subsidy_amount")
    private BigDecimal subsidyAmount;

    @Column(name = "farmer_contribution")
    private BigDecimal farmerContribution;

    @Column(name = "status")
    private String status;

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