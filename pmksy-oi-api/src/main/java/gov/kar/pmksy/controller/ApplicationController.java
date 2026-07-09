package gov.kar.pmksy.controller;

import gov.kar.pmksy.dto.application.ApplicationRequestDto;
import gov.kar.pmksy.dto.application.ApplicationResponseDto;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import gov.kar.pmksy.dto.application.ApprovalRequestDto;
import gov.kar.pmksy.dto.application.WorkflowResponseDto;
import java.util.List;
import gov.kar.pmksy.dto.dashboard.DashboardSummaryDto;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{applicationId}/taluk-approve")
    public ApiResponse<ApplicationResponseDto> talukApprove(
            @PathVariable Long applicationId,
            @RequestBody ApprovalRequestDto request) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application approved by Taluk")
                .data(applicationService.talukApprove(
                        applicationId,
                        request.getUserId(),
                        request.getRemarks()))
                .build();
    }
@GetMapping("/taluk/dashboard/{talukName}")
public DashboardSummaryDto getTalukDashboardSummary(
        @PathVariable String talukName) {

    return applicationService.getTalukDashboardSummary(
            talukName);
}

@GetMapping("/district/dashboard/{districtName}")
public DashboardSummaryDto getDistrictDashboardSummary(
        @PathVariable String districtName) {

    return applicationService.getDistrictDashboardSummary(
            districtName);
}
    @GetMapping
    public ApiResponse<List<ApplicationResponseDto>>
    getAllApplications() {

        return ApiResponse
                .<List<ApplicationResponseDto>>builder()
                .success(true)
                .message("Applications fetched successfully")
                .data(
                        applicationService
                                .getAllApplications())
                .build();
    }

    @GetMapping("/pending/taluk/{talukName}")
    public ApiResponse<List<ApplicationResponseDto>>
    getPendingTalukApplications(
            @PathVariable String talukName) {

        return ApiResponse
                .<List<ApplicationResponseDto>>builder()
                .success(true)
                .message("Pending Taluk applications fetched successfully")
                .data(
                        applicationService
                                .getPendingTalukApplications(
                                        talukName))
                .build();
    }

    @GetMapping("/pending/district/{districtName}")
    public ApiResponse<List<ApplicationResponseDto>>
    getPendingDistrictApplications(
            @PathVariable String districtName) {

        return ApiResponse
                .<List<ApplicationResponseDto>>builder()
                .success(true)
                .message("Pending District applications fetched successfully")
                .data(
                        applicationService
                                .getPendingDistrictApplications(
                                        districtName))
                .build();
    }

    @GetMapping("/taluk/{talukName}/status/{status}")
    public ApiResponse<List<ApplicationResponseDto>>
    getTalukApplicationsByStatus(
            @PathVariable String talukName,
            @PathVariable String status) {

        return ApiResponse
                .<List<ApplicationResponseDto>>builder()
                .success(true)
                .message("Taluk applications fetched successfully")
                .data(
                        applicationService
                                .getTalukApplicationsByStatus(
                                        talukName, status))
                .build();
    }

    @GetMapping("/district/{districtName}/status/{status}")
    public ApiResponse<List<ApplicationResponseDto>>
    getDistrictApplicationsByStatus(
            @PathVariable String districtName,
            @PathVariable String status) {

        return ApiResponse
                .<List<ApplicationResponseDto>>builder()
                .success(true)
                .message("District applications fetched successfully")
                .data(
                        applicationService
                                .getDistrictApplicationsByStatus(
                                        districtName, status))
                .build();
    }

    @GetMapping("/state/status/{status}")
    public ApiResponse<List<ApplicationResponseDto>>
    getStateApplicationsByStatus(
            @PathVariable String status) {

        return ApiResponse
                .<List<ApplicationResponseDto>>builder()
                .success(true)
                .message("State applications fetched successfully")
                .data(
                        applicationService
                                .getStateApplicationsByStatus(
                                        status))
                .build();
    }

    @PostMapping("/{applicationId}/taluk-reject")
    public ApiResponse<ApplicationResponseDto> talukReject(
            @PathVariable Long applicationId,
            @RequestBody ApprovalRequestDto request) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application rejected by Taluk")
                .data(applicationService.talukReject(
                        applicationId,
                        request.getUserId(),
                        request.getRemarks()))
                .build();
    }

    @PostMapping("/{applicationId}/district-approve")
    public ApiResponse<ApplicationResponseDto> districtApprove(
            @PathVariable Long applicationId,
            @RequestBody ApprovalRequestDto request) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application approved by District")
                .data(applicationService.districtApprove(
                        applicationId,
                        request.getUserId(),
                        request.getRemarks()))
                .build();
    }

    @PostMapping("/{applicationId}/district-reject")
    public ApiResponse<ApplicationResponseDto> districtReject(
            @PathVariable Long applicationId,
            @RequestBody ApprovalRequestDto request) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application rejected by District")
                .data(applicationService.districtReject(
                        applicationId,
                        request.getUserId(),
                        request.getRemarks()))
                .build();
    }

    @GetMapping("/{applicationId}/workflow")
    public ApiResponse<List<WorkflowResponseDto>>
    getWorkflowHistory(
            @PathVariable Long applicationId) {

        return ApiResponse
                .<List<WorkflowResponseDto>>builder()
                .success(true)
                .message("Workflow history fetched successfully")
                .data(
                        applicationService
                                .getWorkflowHistory(
                                        applicationId))
                .build();
    }

    @PostMapping
    public ApiResponse<ApplicationResponseDto> createApplication(
            @Valid @RequestBody ApplicationRequestDto request) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application created successfully")
                .data(applicationService.createApplication(request))
                .build();
    }

    @GetMapping("/{applicationId}")
    public ApiResponse<ApplicationResponseDto> getApplicationById(
            @PathVariable Long applicationId) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application fetched successfully")
                .data(applicationService.getApplicationById(applicationId))
                .build();
    }

    @GetMapping("/number/{applicationNumber}")
    public ApiResponse<ApplicationResponseDto> getApplicationByNumber(
            @PathVariable String applicationNumber) {

        return ApiResponse.<ApplicationResponseDto>builder()
                .success(true)
                .message("Application fetched successfully")
                .data(applicationService.getApplicationByNumber(applicationNumber))
                .build();
    }
}