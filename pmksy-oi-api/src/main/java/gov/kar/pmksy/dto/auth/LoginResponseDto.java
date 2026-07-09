package gov.kar.pmksy.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

    private Long userId;

    private String username;

    private String fullName;

    private String roleCode;

    private String roleName;

    private String districtName;

    private String talukName;
}