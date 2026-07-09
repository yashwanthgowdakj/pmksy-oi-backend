package gov.kar.pmksy.dto.dashboard;

import lombok.Data;

@Data
public class DashboardSummaryDto {

    private long pending;

    private long approved;

    private long rejected;

    private long completed;

}