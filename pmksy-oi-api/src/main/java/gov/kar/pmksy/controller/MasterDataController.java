package gov.kar.pmksy.controller;

import gov.kar.pmksy.entity.MstAdministrativeDistrict;
import gov.kar.pmksy.entity.MstAdministrativeHobli;
import gov.kar.pmksy.entity.MstAdministrativeTaluk;
import gov.kar.pmksy.entity.MstAdministrativeVillage;
import gov.kar.pmksy.entity.MstGender;
import gov.kar.pmksy.entity.MstSocialCategory;
import gov.kar.pmksy.payload.ApiResponse;
import gov.kar.pmksy.service.MasterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master")
@RequiredArgsConstructor
public class MasterDataController {

    private final MasterDataService masterDataService;

    @GetMapping("/districts")
    public ApiResponse<List<MstAdministrativeDistrict>> getDistricts() {

        return ApiResponse.<List<MstAdministrativeDistrict>>builder()
                .success(true)
                .message("Districts fetched successfully")
                .data(masterDataService.getDistricts())
                .build();
    }

    @GetMapping("/taluks/{districtId}")
    public ApiResponse<List<MstAdministrativeTaluk>> getTaluks(
            @PathVariable Long districtId) {

        return ApiResponse.<List<MstAdministrativeTaluk>>builder()
                .success(true)
                .message("Taluks fetched successfully")
                .data(masterDataService.getTaluks(districtId))
                .build();
    }

    @GetMapping("/hoblis/{talukId}")
    public ApiResponse<List<MstAdministrativeHobli>> getHoblis(
            @PathVariable Long talukId) {

        return ApiResponse.<List<MstAdministrativeHobli>>builder()
                .success(true)
                .message("Hoblis fetched successfully")
                .data(masterDataService.getHoblis(talukId))
                .build();
    }

    @GetMapping("/villages/{hobliId}")
    public ApiResponse<List<MstAdministrativeVillage>> getVillages(
            @PathVariable Long hobliId) {

        return ApiResponse.<List<MstAdministrativeVillage>>builder()
                .success(true)
                .message("Villages fetched successfully")
                .data(masterDataService.getVillages(hobliId))
                .build();
    }

    @GetMapping("/genders")
    public ApiResponse<List<MstGender>> getGenders() {

        return ApiResponse.<List<MstGender>>builder()
                .success(true)
                .message("Genders fetched successfully")
                .data(masterDataService.getGenders())
                .build();
    }

    @GetMapping("/social-categories")
    public ApiResponse<List<MstSocialCategory>> getSocialCategories() {

        return ApiResponse.<List<MstSocialCategory>>builder()
                .success(true)
                .message("Social Categories fetched successfully")
                .data(masterDataService.getSocialCategories())
                .build();
    }
}