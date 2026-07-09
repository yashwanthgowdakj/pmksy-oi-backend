package gov.kar.pmksy.service;

import gov.kar.pmksy.dto.infrastructure.OrchardInfrastructureRequestDto;
import gov.kar.pmksy.dto.infrastructure.OrchardInfrastructureResponseDto;

import java.util.List;

public interface OrchardInfrastructureService {

    OrchardInfrastructureResponseDto createInfrastructure(
            OrchardInfrastructureRequestDto request);

    List<OrchardInfrastructureResponseDto>
    getInfrastructureByOrchard(
            Long orchardId);
}