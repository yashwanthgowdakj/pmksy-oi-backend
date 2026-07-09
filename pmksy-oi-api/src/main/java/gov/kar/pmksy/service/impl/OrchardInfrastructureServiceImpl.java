package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.dto.infrastructure.OrchardInfrastructureRequestDto;
import gov.kar.pmksy.dto.infrastructure.OrchardInfrastructureResponseDto;
import gov.kar.pmksy.entity.TrnOrchardDetails;
import gov.kar.pmksy.entity.TrnOrchardInfrastructure;
import gov.kar.pmksy.repository.OrchardDetailsRepository;
import gov.kar.pmksy.repository.OrchardInfrastructureRepository;
import gov.kar.pmksy.service.OrchardInfrastructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrchardInfrastructureServiceImpl
        implements OrchardInfrastructureService {

    private final OrchardInfrastructureRepository infrastructureRepository;

    private final OrchardDetailsRepository orchardRepository;

    @Override
    public OrchardInfrastructureResponseDto createInfrastructure(
            OrchardInfrastructureRequestDto request) {

        TrnOrchardDetails orchard =
                orchardRepository.findById(
                                request.getOrchardId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Orchard not found"));

        TrnOrchardInfrastructure infrastructure =
                new TrnOrchardInfrastructure();

        infrastructure.setOrchard(orchard);
        infrastructure.setInfrastructureType(
                request.getInfrastructureType());

        infrastructure.setUnit(
                request.getUnit());

        infrastructure.setQuantity(
                request.getQuantity());

        infrastructure.setEstimatedCost(
                request.getEstimatedCost());

        infrastructure.setSubsidyAmount(
                request.getSubsidyAmount());

        infrastructure.setFarmerContribution(
                request.getFarmerContribution());

        infrastructure.setStatus(
                request.getStatus());

        infrastructure.setActiveFlag(true);
        infrastructure.setCreatedDate(
                LocalDateTime.now());

        infrastructure =
                infrastructureRepository.save(
                        infrastructure);

        return mapToResponse(
                infrastructure);
    }

    @Override
    public List<OrchardInfrastructureResponseDto>
    getInfrastructureByOrchard(
            Long orchardId) {

        return infrastructureRepository
                .findByOrchardOrchardId(
                        orchardId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OrchardInfrastructureResponseDto
    mapToResponse(
            TrnOrchardInfrastructure infrastructure) {

        OrchardInfrastructureResponseDto dto =
                new OrchardInfrastructureResponseDto();

        dto.setInfrastructureId(
                infrastructure.getInfrastructureId());

        dto.setOrchardId(
                infrastructure.getOrchard()
                        .getOrchardId());

        dto.setInfrastructureType(
                infrastructure.getInfrastructureType());

        dto.setUnit(
                infrastructure.getUnit());

        dto.setQuantity(
                infrastructure.getQuantity());

        dto.setEstimatedCost(
                infrastructure.getEstimatedCost());

        dto.setSubsidyAmount(
                infrastructure.getSubsidyAmount());

        dto.setFarmerContribution(
                infrastructure.getFarmerContribution());

        dto.setStatus(
                infrastructure.getStatus());

        dto.setActiveFlag(
                infrastructure.getActiveFlag());

        return dto;
    }
}