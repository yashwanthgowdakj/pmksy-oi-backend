package gov.kar.pmksy.controller;

import gov.kar.pmksy.dto.auth.LoginRequestDto;
import gov.kar.pmksy.dto.auth.LoginResponseDto;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(
            @RequestBody LoginRequestDto request) {

        return ApiResponse.<LoginResponseDto>builder()
                .success(true)
                .message("Login Successful")
                .data(authService.login(request))
                .build();
    }
}