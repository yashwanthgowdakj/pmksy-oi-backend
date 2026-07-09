package gov.kar.pmksy.service;

import gov.kar.pmksy.dto.orchard.OrchardDetailsRequestDto;
import gov.kar.pmksy.dto.orchard.OrchardDetailsResponseDto;

import java.util.List;

public interface OrchardDetailsService {

    OrchardDetailsResponseDto createOrchard(
            OrchardDetailsRequestDto request);

    List<OrchardDetailsResponseDto>
    getOrchardsByApplication(
            Long applicationId);
}