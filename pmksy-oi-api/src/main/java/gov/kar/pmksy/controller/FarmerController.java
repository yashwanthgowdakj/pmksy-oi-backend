package gov.kar.pmksy.controller;

import gov.kar.pmksy.dto.farmer.FarmerRequestDto;
import gov.kar.pmksy.dto.farmer.FarmerResponseDto;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.FarmerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping
    public ApiResponse<FarmerResponseDto> createFarmer(
            @Valid @RequestBody FarmerRequestDto request) {

        return ApiResponse.<FarmerResponseDto>builder()
                .success(true)
                .message("Farmer registered successfully")
                .data(farmerService.createFarmer(request))
                .build();
    }

    @GetMapping("/{farmerId}")
    public ApiResponse<FarmerResponseDto> getFarmerById(
            @PathVariable Long farmerId) {

        return ApiResponse.<FarmerResponseDto>builder()
                .success(true)
                .message("Farmer fetched successfully")
                .data(farmerService.getFarmerById(farmerId))
                .build();
    }

    @GetMapping("/fid/{fidNumber}")
    public ApiResponse<FarmerResponseDto> getFarmerByFID(
            @PathVariable String fidNumber) {

        return ApiResponse.<FarmerResponseDto>builder()
                .success(true)
                .message("Farmer fetched successfully")
                .data(farmerService.getFarmerByFID(fidNumber))
                .build();
    }
}