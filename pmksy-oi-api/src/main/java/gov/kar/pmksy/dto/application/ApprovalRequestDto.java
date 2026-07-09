package gov.kar.pmksy.dto.application;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApprovalRequestDto {

    @NotNull
    private Long userId;

    private String remarks;
}