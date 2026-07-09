package gov.kar.pmksy.service;

import gov.kar.pmksy.dto.farmer.FarmerRequestDto;
import gov.kar.pmksy.dto.farmer.FarmerResponseDto;

public interface FarmerService {

    FarmerResponseDto createFarmer(FarmerRequestDto request);

    FarmerResponseDto getFarmerById(Long farmerId);

    FarmerResponseDto getFarmerByFID(String fidNumber);
}