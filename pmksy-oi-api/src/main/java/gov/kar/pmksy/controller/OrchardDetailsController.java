package gov.kar.pmksy.controller;

import gov.kar.pmksy.dto.orchard.OrchardDetailsRequestDto;
import gov.kar.pmksy.dto.orchard.OrchardDetailsResponseDto;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.OrchardDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orchards")
@RequiredArgsConstructor
public class OrchardDetailsController {

    private final OrchardDetailsService orchardService;

    @PostMapping
    public ApiResponse<OrchardDetailsResponseDto>
    createOrchard(
            @RequestBody
            OrchardDetailsRequestDto request) {

        return ApiResponse
                .<OrchardDetailsResponseDto>builder()
                .success(true)
                .message("Orchard created successfully")
                .data(
                        orchardService
                                .createOrchard(request))
                .build();
    }

    @GetMapping(
            "/application/{applicationId}")
    public ApiResponse<
            List<OrchardDetailsResponseDto>>
    getOrchardsByApplication(
            @PathVariable
            Long applicationId) {

        return ApiResponse
                .<List<OrchardDetailsResponseDto>>
                        builder()
                .success(true)
                .message(
                        "Orchards fetched successfully")
                .data(
                        orchardService
                                .getOrchardsByApplication(
                                        applicationId))
                .build();
    }
}