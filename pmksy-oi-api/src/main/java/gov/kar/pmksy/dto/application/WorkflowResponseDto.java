package gov.kar.pmksy.dto.application;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkflowResponseDto {

    private Long workflowId;

    private String actionTaken;

    private Long actionBy;

    private String officerName;

    private String officerRole;

    private LocalDateTime actionDate;

    private String remarks;
}