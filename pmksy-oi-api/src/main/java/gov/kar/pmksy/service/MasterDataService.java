package gov.kar.pmksy.service;

import gov.kar.pmksy.entity.*;

import java.util.List;

public interface MasterDataService {

    List<MstAdministrativeDistrict> getDistricts();

    List<MstAdministrativeTaluk> getTaluks(Long districtId);

    List<MstAdministrativeHobli> getHoblis(Long talukId);

    List<MstAdministrativeVillage> getVillages(Long hobliId);

    List<MstGender> getGenders();

    List<MstSocialCategory> getSocialCategories();
}