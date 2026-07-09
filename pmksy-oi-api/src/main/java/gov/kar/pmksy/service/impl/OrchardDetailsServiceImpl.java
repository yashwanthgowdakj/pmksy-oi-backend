package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.dto.orchard.OrchardDetailsRequestDto;
import gov.kar.pmksy.dto.orchard.OrchardDetailsResponseDto;
import gov.kar.pmksy.entity.TrnFarmerApplication;
import gov.kar.pmksy.entity.TrnOrchardDetails;
import gov.kar.pmksy.repository.ApplicationRepository;
import gov.kar.pmksy.repository.OrchardDetailsRepository;
import gov.kar.pmksy.service.OrchardDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrchardDetailsServiceImpl
        implements OrchardDetailsService {

    private final OrchardDetailsRepository orchardRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public OrchardDetailsResponseDto createOrchard(
            OrchardDetailsRequestDto request) {

        TrnFarmerApplication application =
                applicationRepository.findById(
                        request.getApplicationId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Application not found"));

        TrnOrchardDetails orchard =
                new TrnOrchardDetails();

        orchard.setApplication(application);
        orchard.setCropName(request.getCropName());
        orchard.setVarietyName(request.getVarietyName());
        orchard.setAreaAcre(request.getAreaAcre());
        orchard.setPlantCount(request.getPlantCount());
        orchard.setIrrigationType(request.getIrrigationType());
        orchard.setSpacing(request.getSpacing());
        orchard.setOrchardStatus(request.getOrchardStatus());
        orchard.setSurveyNumber(request.getSurveyNumber());
        orchard.setLatitude(request.getLatitude());
        orchard.setLongitude(request.getLongitude());

        orchard.setActiveFlag(true);
        orchard.setCreatedDate(LocalDateTime.now());

        orchard = orchardRepository.save(orchard);

        return mapToResponse(orchard);
    }

    @Override
    public List<OrchardDetailsResponseDto>
    getOrchardsByApplication(
            Long applicationId) {

        return orchardRepository
                .findByApplicationApplicationId(
                        applicationId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OrchardDetailsResponseDto
    mapToResponse(
            TrnOrchardDetails orchard) {

        OrchardDetailsResponseDto dto =
                new OrchardDetailsResponseDto();

        dto.setOrchardId(
                orchard.getOrchardId());

        dto.setApplicationId(
                orchard.getApplication()
                        .getApplicationId());

        dto.setCropName(
                orchard.getCropName());

        dto.setVarietyName(
                orchard.getVarietyName());

        dto.setAreaAcre(
                orchard.getAreaAcre());

        dto.setPlantCount(
                orchard.getPlantCount());

        dto.setIrrigationType(
                orchard.getIrrigationType());

        dto.setSpacing(
                orchard.getSpacing());

        dto.setOrchardStatus(
                orchard.getOrchardStatus());

        dto.setSurveyNumber(
                orchard.getSurveyNumber());

        dto.setLatitude(
                orchard.getLatitude());

        dto.setLongitude(
                orchard.getLongitude());

        dto.setActiveFlag(
                orchard.getActiveFlag());

        return dto;
    }
}