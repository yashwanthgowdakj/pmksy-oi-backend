package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trn_application_workflow")
public class TrnApplicationWorkflow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workflow_id")
    private Long workflowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private TrnFarmerApplication application;

    @Column(name = "action_taken")
    private String actionTaken;

    @Column(name = "action_by")
    private Long actionBy;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    @Column(name = "remarks")
    private String remarks;
}