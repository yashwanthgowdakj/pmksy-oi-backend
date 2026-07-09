package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.dto.application.ApplicationRequestDto;
import gov.kar.pmksy.dto.application.ApplicationResponseDto;
import gov.kar.pmksy.entity.MstFarmer;
import gov.kar.pmksy.entity.TrnApplicationWorkflow;
import gov.kar.pmksy.entity.TrnFarmerApplication;
import gov.kar.pmksy.repository.ApplicationRepository;
import gov.kar.pmksy.repository.ApplicationWorkflowRepository;
import gov.kar.pmksy.repository.FarmerRepository;
import gov.kar.pmksy.repository.UserRepository;
import gov.kar.pmksy.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.kar.pmksy.entity.TrnApplicationDetails;
import gov.kar.pmksy.repository.TrnApplicationDetailsRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import gov.kar.pmksy.dto.application.WorkflowResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import gov.kar.pmksy.dto.dashboard.DashboardSummaryDto;

@Service
@RequiredArgsConstructor
@Transactional

public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final FarmerRepository farmerRepository;

    private final ApplicationWorkflowRepository workflowRepository;
    private final TrnApplicationDetailsRepository applicationDetailsRepository;
    private final UserRepository userRepository;

@Override
public DashboardSummaryDto getTalukDashboardSummary(
        String talukName) {

    DashboardSummaryDto dto = new DashboardSummaryDto();

    dto.setPending(
            applicationRepository.countByCurrentLevelAndTalukName(
                    "TALUK",
                    talukName));

    dto.setApproved(
            applicationRepository.countByApplicationStatusAndTalukName(
                    "TALUK_APPROVED",
                    talukName));

    dto.setRejected(
            applicationRepository.countByApplicationStatusAndTalukName(
                    "TALUK_REJECTED",
                    talukName));

    dto.setCompleted(
            applicationRepository
                    .countByCurrentLevelAndApplicationStatusAndTalukName(
                            "COMPLETED",
                            "DISTRICT_APPROVED",
                            talukName));

    return dto;
}

@Override
public DashboardSummaryDto getDistrictDashboardSummary(
        String districtName) {

    DashboardSummaryDto dto = new DashboardSummaryDto();

    dto.setPending(
            applicationRepository.countByCurrentLevelAndDistrictName(
                    "DISTRICT",
                    districtName));

    dto.setApproved(
            applicationRepository.countByApplicationStatusAndDistrictName(
                    "DISTRICT_APPROVED",
                    districtName));

    dto.setRejected(
            applicationRepository.countByApplicationStatusAndDistrictName(
                    "DISTRICT_REJECTED",
                    districtName));

    dto.setCompleted(
            applicationRepository
                    .countByCurrentLevelAndApplicationStatusAndDistrictName(
                            "COMPLETED",
                            "DISTRICT_APPROVED",
                            districtName));

    return dto;
}
    @Override
    public ApplicationResponseDto createApplication(
            ApplicationRequestDto request) {

        MstFarmer farmer =
                farmerRepository.findById(
                                request.getFarmerId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Farmer not found"));

        TrnFarmerApplication application =
                new TrnFarmerApplication();

        application.setApplicationNumber(
                generateApplicationNumber());

        application.setFarmer(
                farmer);

        application.setApplicationDate(
                LocalDate.now());

        application.setFinancialYear(
                request.getFinancialYear());

        application.setApplicationStatus(
                "SUBMITTED");

        application.setCurrentLevel(
                "TALUK");

        application.setTalukName(
                farmer.getTaluk().getTalukName());

        application.setDistrictName(
                farmer.getDistrict().getDistrictName());

        application.setRemarks(
                request.getRemarks());

        application =
                applicationRepository.save(
                        application);

        /*
         * Save Application Details
         */
        TrnApplicationDetails details =
                new TrnApplicationDetails();

        details.setApplication(
                application);

        details.setSurveyNumber(
                request.getSurveyNumber());

        details.setVillageName(
                request.getVillageName());

        details.setExtentAcre(
                request.getExtentAcre());

        details.setInterventionType(
                request.getInterventionType());

        details.setEstimatedCost(
                request.getEstimatedCost());

        applicationDetailsRepository.save(
                details);

        /*
         * Save Workflow Entry
         */
        saveWorkflow(
                application,
                "SUBMITTED",
                0L,
                "Application Submitted");

        return mapToResponse(
                application);
    }

    @Override
    public ApplicationResponseDto getApplicationById(
            Long applicationId) {

        TrnFarmerApplication application =
                applicationRepository.findById(
                                applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        return mapToResponse(application);
    }

    @Override
    public ApplicationResponseDto getApplicationByNumber(
            String applicationNumber) {

        TrnFarmerApplication application =
                applicationRepository
                        .findByApplicationNumber(
                                applicationNumber)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        return mapToResponse(application);
    }

    @Override
    public List<ApplicationResponseDto> getAllApplications() {

        return applicationRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getPendingTalukApplications(
            String talukName) {

        return applicationRepository
                .findByCurrentLevelAndTalukName(
                        "TALUK",
                        talukName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getTalukApplications(
            String talukName) {

        return applicationRepository
                .findByTalukNameOrderByApplicationDateDesc(
                        talukName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getPendingDistrictApplications(
            String districtName) {

        return applicationRepository
                .findByCurrentLevelAndDistrictName(
                        "DISTRICT",
                        districtName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getTalukApplicationsByStatus(
            String talukName,
            String status) {

        List<TrnFarmerApplication> apps;

        switch (status.toUpperCase()) {

            case "PENDING":
                apps = applicationRepository
                        .findByCurrentLevelAndTalukName(
                                "TALUK", talukName);
                break;

            case "APPROVED":
                apps = applicationRepository
                        .findByApplicationStatusAndTalukName(
                                "TALUK_APPROVED", talukName);
                break;

            case "REJECTED":
                apps = applicationRepository
                        .findByApplicationStatusAndTalukName(
                                "TALUK_REJECTED", talukName);
                break;

            case "COMPLETED":
                apps = applicationRepository
                        .findByCurrentLevelAndApplicationStatusAndTalukName(
                                "COMPLETED", "DISTRICT_APPROVED", talukName);
                break;

            default:
                throw new RuntimeException("Invalid status: " + status);
        }

        return apps.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getDistrictApplicationsByStatus(
            String districtName,
            String status) {

        List<TrnFarmerApplication> apps;

        switch (status.toUpperCase()) {

            case "PENDING":
                apps = applicationRepository
                        .findByCurrentLevelAndDistrictName(
                                "DISTRICT", districtName);
                break;

            case "APPROVED":
                apps = applicationRepository
                        .findByApplicationStatusAndDistrictName(
                                "DISTRICT_APPROVED", districtName);
                break;

            case "REJECTED":
                apps = applicationRepository
                        .findByApplicationStatusAndDistrictName(
                                "DISTRICT_REJECTED", districtName);
                break;

            case "COMPLETED":
                apps = applicationRepository
                        .findByCurrentLevelAndApplicationStatusAndDistrictName(
                                "COMPLETED", "DISTRICT_APPROVED", districtName);
                break;

            default:
                throw new RuntimeException("Invalid status: " + status);
        }

        return apps.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getStateApplicationsByStatus(
            String status) {

        List<TrnFarmerApplication> all = applicationRepository.findAll();

        List<TrnFarmerApplication> filtered;

        switch (status.toUpperCase()) {

            case "PENDING":
                filtered = all.stream()
                        .filter(a -> "TALUK".equals(a.getCurrentLevel())
                                || "DISTRICT".equals(a.getCurrentLevel()))
                        .collect(Collectors.toList());
                break;

            case "APPROVED":
                filtered = all.stream()
                        .filter(a -> "TALUK_APPROVED".equals(a.getApplicationStatus())
                                || "DISTRICT_APPROVED".equals(a.getApplicationStatus()))
                        .collect(Collectors.toList());
                break;

            case "REJECTED":
                filtered = all.stream()
                        .filter(a -> a.getApplicationStatus() != null
                                && a.getApplicationStatus().contains("REJECTED"))
                        .collect(Collectors.toList());
                break;

            case "COMPLETED":
                filtered = all.stream()
                        .filter(a -> "COMPLETED".equals(a.getCurrentLevel()))
                        .collect(Collectors.toList());
                break;

            default:
                throw new RuntimeException("Invalid status: " + status);
        }

        return filtered.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponseDto talukApprove(
            Long applicationId,
            Long userId,
            String remarks) {

        TrnFarmerApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        application.setApplicationStatus(
                "TALUK_APPROVED");

        application.setCurrentLevel(
                "DISTRICT");

        application.setTalukVerifiedBy(
                userId);

        application.setTalukVerifiedDate(
                LocalDateTime.now());

        application.setRemarks(
                remarks);

        application =
                applicationRepository.save(
                        application);

        saveWorkflow(
                application,
                "TALUK_APPROVED",
                userId,
                remarks);

        return mapToResponse(application);
    }

    @Override
    public ApplicationResponseDto talukReject(
            Long applicationId,
            Long userId,
            String remarks) {

        TrnFarmerApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        application.setApplicationStatus(
                "TALUK_REJECTED");

        application.setCurrentLevel(
                "REJECTED");

        application.setTalukVerifiedBy(
                userId);

        application.setTalukVerifiedDate(
                LocalDateTime.now());

        application.setRemarks(
                remarks);

        application =
                applicationRepository.save(
                        application);

        saveWorkflow(
                application,
                "TALUK_REJECTED",
                userId,
                remarks);

        return mapToResponse(application);
    }

    @Override
    public List<WorkflowResponseDto> getWorkflowHistory(
            Long applicationId) {

        return workflowRepository
                .findByApplicationApplicationId(
                        applicationId)
                .stream()
                .map(workflow -> {

                    WorkflowResponseDto dto =
                            new WorkflowResponseDto();

                    dto.setWorkflowId(
                            workflow.getWorkflowId());

                    dto.setActionTaken(
                            workflow.getActionTaken());

                    dto.setActionBy(
                            workflow.getActionBy());

                    if (workflow.getActionBy() != null) {

                        userRepository
                                .findById(workflow.getActionBy())
                                .ifPresent(user -> {

                                    dto.setOfficerName(
                                            user.getFullName());

                                    dto.setOfficerRole(
                                            user.getRole() != null
                                                    ? user.getRole().getRoleName()
                                                    : null);
                                });
                    }

                    dto.setActionDate(
                            workflow.getActionDate());

                    dto.setRemarks(
                            workflow.getRemarks());

                    return dto;

                }).collect(Collectors.toList());
    }

    @Override
    public ApplicationResponseDto districtApprove(
            Long applicationId,
            Long userId,
            String remarks) {

        TrnFarmerApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        if ("DISTRICT_APPROVED".equals(
                application.getApplicationStatus())) {

            throw new RuntimeException(
                    "Application already approved by District");
        }

        if (!"TALUK_APPROVED".equals(
                application.getApplicationStatus())) {

            throw new RuntimeException(
                    "Application must be approved by Taluk first");
        }

        application.setApplicationStatus(
                "DISTRICT_APPROVED");

        application.setCurrentLevel(
                "COMPLETED");

        application.setDistrictVerifiedBy(
                userId);

        application.setDistrictVerifiedDate(
                LocalDateTime.now());

        application.setRemarks(
                remarks);

        application =
                applicationRepository.save(
                        application);

        saveWorkflow(
                application,
                "DISTRICT_APPROVED",
                userId,
                remarks);

        return mapToResponse(application);
    }

    @Override
    public ApplicationResponseDto districtReject(
            Long applicationId,
            Long userId,
            String remarks) {

        TrnFarmerApplication application =
                applicationRepository.findById(applicationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        application.setApplicationStatus(
                "DISTRICT_REJECTED");

        application.setCurrentLevel(
                "REJECTED");

        application.setDistrictVerifiedBy(
                userId);

        application.setDistrictVerifiedDate(
                LocalDateTime.now());

        application.setRemarks(
                remarks);

        application =
                applicationRepository.save(
                        application);

        saveWorkflow(
                application,
                "DISTRICT_REJECTED",
                userId,
                remarks);

        return mapToResponse(application);
    }

    private void saveWorkflow(
            TrnFarmerApplication application,
            String actionTaken,
            Long userId,
            String remarks) {

        TrnApplicationWorkflow workflow =
                new TrnApplicationWorkflow();

        workflow.setApplication(
                application);

        workflow.setActionTaken(
                actionTaken);

        workflow.setActionBy(
                userId);

        workflow.setActionDate(
                LocalDateTime.now());

        workflow.setRemarks(
                remarks);

        workflowRepository.save(
                workflow);
    }

    private String generateApplicationNumber() {

        long count =
                applicationRepository.count() + 1;

        return "PMKSY-"
                + Year.now().getValue()
                + "-"
                + String.format("%06d", count);
    }

    private ApplicationResponseDto mapToResponse(
            TrnFarmerApplication application) {

        ApplicationResponseDto dto =
                new ApplicationResponseDto();

        dto.setApplicationId(
                application.getApplicationId());

        dto.setApplicationNumber(
                application.getApplicationNumber());

        dto.setFarmerId(
                application.getFarmer().getFarmerId());

        dto.setFarmerName(
                application.getFarmer().getFarmerName());

        dto.setApplicationDate(
                application.getApplicationDate());

        dto.setFinancialYear(
                application.getFinancialYear());

        dto.setApplicationStatus(
                application.getApplicationStatus());

        dto.setCurrentLevel(
                application.getCurrentLevel());

        dto.setTalukName(
                application.getTalukName());

        dto.setDistrictName(
                application.getDistrictName());

        dto.setRemarks(
                application.getRemarks());

        dto.setActiveFlag(
                application.getActiveFlag());

        return dto;
    }
}