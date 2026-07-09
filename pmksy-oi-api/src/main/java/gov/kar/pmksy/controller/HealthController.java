package gov.kar.pmksy.controller;

import gov.kar.pmksy.payload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public ApiResponse<String> health() {

        return ApiResponse.<String>builder()
                .success(true)
                .message("PMKSY-OI API Running")
                .data("SUCCESS")
                .build();
    }
}