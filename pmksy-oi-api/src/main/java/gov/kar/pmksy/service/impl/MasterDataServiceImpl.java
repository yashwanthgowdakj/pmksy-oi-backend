package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.entity.*;
import gov.kar.pmksy.repository.*;
import gov.kar.pmksy.service.MasterDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterDataServiceImpl implements MasterDataService {

    private final DistrictRepository districtRepository;
    private final TalukRepository talukRepository;
    private final HobliRepository hobliRepository;
    private final VillageRepository villageRepository;
    private final GenderRepository genderRepository;
    private final SocialCategoryRepository socialCategoryRepository;

    @Override
    public List<MstAdministrativeDistrict> getDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public List<MstAdministrativeTaluk> getTaluks(Long districtId) {
        return talukRepository.findByDistrictDistrictId(districtId);
    }

    @Override
    public List<MstAdministrativeHobli> getHoblis(Long talukId) {
        return hobliRepository.findByTalukTalukId(talukId);
    }

    @Override
    public List<MstAdministrativeVillage> getVillages(Long hobliId) {
        return villageRepository.findByHobliHobliId(hobliId);
    }

    @Override
    public List<MstGender> getGenders() {
        return genderRepository.findAll();
    }

    @Override
    public List<MstSocialCategory> getSocialCategories() {
        return socialCategoryRepository.findAll();
    }
}