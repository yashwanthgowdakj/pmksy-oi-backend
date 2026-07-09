package gov.kar.pmksy.service;

import gov.kar.pmksy.dto.auth.LoginRequestDto;
import gov.kar.pmksy.dto.auth.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(
            LoginRequestDto request);
}