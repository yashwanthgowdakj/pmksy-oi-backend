package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.dto.farmer.FarmerRequestDto;
import gov.kar.pmksy.dto.farmer.FarmerResponseDto;
import gov.kar.pmksy.entity.*;
import gov.kar.pmksy.repository.*;
import gov.kar.pmksy.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    private final GenderRepository genderRepository;
    private final SocialCategoryRepository socialCategoryRepository;
    private final DistrictRepository districtRepository;
    private final TalukRepository talukRepository;
    private final HobliRepository hobliRepository;
    private final VillageRepository villageRepository;

    @Override
    public FarmerResponseDto createFarmer(FarmerRequestDto request) {

        if (farmerRepository.existsByFidNumber(request.getFidNumber())) {
            throw new RuntimeException(
                    "Farmer already exists with FID : "
                            + request.getFidNumber());
        }

        MstGender gender =
                genderRepository.findById(request.getGenderId())
                        .orElseThrow(() ->
                                new RuntimeException("Invalid Gender"));

        MstSocialCategory socialCategory =
                socialCategoryRepository.findById(
                                request.getSocialCategoryId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid Social Category"));

        MstAdministrativeDistrict district =
                districtRepository.findById(
                                request.getDistrictId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid District"));

        MstAdministrativeTaluk taluk =
                talukRepository.findById(
                                request.getTalukId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid Taluk"));

        MstAdministrativeHobli hobli =
                hobliRepository.findById(
                                request.getHobliId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid Hobli"));

        MstAdministrativeVillage village =
                villageRepository.findById(
                                request.getVillageId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid Village"));

        MstFarmer farmer = new MstFarmer();

        farmer.setFidNumber(request.getFidNumber());
        farmer.setFarmerName(request.getFarmerName());
        farmer.setFatherName(request.getFatherName());
        farmer.setMobileNumber(request.getMobileNumber());
        farmer.setAadhaarMasked(request.getAadhaarMasked());
        farmer.setTotalLandExtent(request.getTotalLandExtent());

        farmer.setGender(gender);
        farmer.setSocialCategory(socialCategory);
        farmer.setDistrict(district);
        farmer.setTaluk(taluk);
        farmer.setHobli(hobli);
        farmer.setVillage(village);

        farmer = farmerRepository.save(farmer);

        return mapToResponse(farmer);
    }

    @Override
@Transactional(readOnly = true)
public FarmerResponseDto getFarmerById(Long farmerId) {

    MstFarmer farmer =
            farmerRepository.findById(farmerId)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Farmer not found"));

    return mapToResponse(farmer);
}

@Override
@Transactional(readOnly = true)
public FarmerResponseDto getFarmerByFID(String fidNumber) {

    MstFarmer farmer =
            farmerRepository.findByFidNumber(fidNumber)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Farmer not found"));

    return mapToResponse(farmer);
}

    private FarmerResponseDto mapToResponse(
            MstFarmer farmer) {

        FarmerResponseDto dto =
                new FarmerResponseDto();

        dto.setFarmerId(farmer.getFarmerId());
        dto.setFidNumber(farmer.getFidNumber());
        dto.setFarmerName(farmer.getFarmerName());
        dto.setFatherName(farmer.getFatherName());
        dto.setMobileNumber(farmer.getMobileNumber());
        dto.setAadhaarMasked(farmer.getAadhaarMasked());
        dto.setTotalLandExtent(
                farmer.getTotalLandExtent());

        dto.setGender(
                farmer.getGender().getGenderName());

        dto.setSocialCategory(
                farmer.getSocialCategory()
                        .getCategoryName());

        dto.setDistrict(
                farmer.getDistrict()
                        .getDistrictName());

        dto.setTaluk(
                farmer.getTaluk()
                        .getTalukName());

        dto.setHobli(
                farmer.getHobli()
                        .getHobliName());

        dto.setVillage(
                farmer.getVillage()
                        .getVillageName());

        dto.setActiveFlag(
                farmer.getActiveFlag());

        return dto;
    }
}