package gov.kar.pmksy.dto.document;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentVerificationRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Boolean verified;

    private String remarks;
}
