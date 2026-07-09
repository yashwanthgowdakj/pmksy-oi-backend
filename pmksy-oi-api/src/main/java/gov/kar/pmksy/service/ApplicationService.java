package gov.kar.pmksy.service;

import gov.kar.pmksy.dto.application.ApplicationRequestDto;
import gov.kar.pmksy.dto.application.ApplicationResponseDto;
import gov.kar.pmksy.dto.application.WorkflowResponseDto;
import gov.kar.pmksy.dto.dashboard.DashboardSummaryDto;

import java.util.List;

public interface ApplicationService {

    ApplicationResponseDto createApplication(
            ApplicationRequestDto request);

    ApplicationResponseDto getApplicationById(
            Long applicationId);

    ApplicationResponseDto getApplicationByNumber(
            String applicationNumber);

    List<ApplicationResponseDto>
    getAllApplications();

    List<ApplicationResponseDto>
    getPendingTalukApplications(
            String talukName);

    DashboardSummaryDto getTalukDashboardSummary(
            String talukName);

    DashboardSummaryDto getDistrictDashboardSummary(
            String districtName);

    // NEW
    List<ApplicationResponseDto>
    getTalukApplications(
            String talukName);

    List<ApplicationResponseDto>
    getPendingDistrictApplications(
            String districtName);

    ApplicationResponseDto talukApprove(
            Long applicationId,
            Long userId,
            String remarks);

    ApplicationResponseDto talukReject(
            Long applicationId,
            Long userId,
            String remarks);

    ApplicationResponseDto districtApprove(
            Long applicationId,
            Long userId,
            String remarks);

    ApplicationResponseDto districtReject(
            Long applicationId,
            Long userId,
            String remarks);

    List<WorkflowResponseDto>
    getWorkflowHistory(
            Long applicationId);

    List<ApplicationResponseDto>
    getTalukApplicationsByStatus(
            String talukName,
            String status);

    List<ApplicationResponseDto>
    getDistrictApplicationsByStatus(
            String districtName,
            String status);

    List<ApplicationResponseDto>
    getStateApplicationsByStatus(
            String status);
}