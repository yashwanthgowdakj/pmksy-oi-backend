package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstAdministrativeTaluk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalukRepository
        extends JpaRepository<MstAdministrativeTaluk, Long> {

    List<MstAdministrativeTaluk>
    findByDistrictDistrictId(Long districtId);
}