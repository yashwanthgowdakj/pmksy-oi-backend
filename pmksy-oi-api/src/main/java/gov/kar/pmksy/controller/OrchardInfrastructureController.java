package gov.kar.pmksy.controller;

import gov.kar.pmksy.dto.infrastructure.OrchardInfrastructureRequestDto;
import gov.kar.pmksy.dto.infrastructure.OrchardInfrastructureResponseDto;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.OrchardInfrastructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/infrastructure")
@RequiredArgsConstructor
public class OrchardInfrastructureController {

    private final OrchardInfrastructureService
            infrastructureService;

    @PostMapping
    public ApiResponse<
            OrchardInfrastructureResponseDto>
    createInfrastructure(
            @RequestBody
            OrchardInfrastructureRequestDto request) {

        return ApiResponse
                .<OrchardInfrastructureResponseDto>
                        builder()
                .success(true)
                .message(
                        "Infrastructure created successfully")
                .data(
                        infrastructureService
                                .createInfrastructure(
                                        request))
                .build();
    }

    @GetMapping(
            "/orchard/{orchardId}")
    public ApiResponse<
            List<OrchardInfrastructureResponseDto>>
    getInfrastructureByOrchard(
            @PathVariable
            Long orchardId) {

        return ApiResponse
                .<List<
                        OrchardInfrastructureResponseDto>>
                        builder()
                .success(true)
                .message(
                        "Infrastructure fetched successfully")
                .data(
                        infrastructureService
                                .getInfrastructureByOrchard(
                                        orchardId))
                .build();
    }
}