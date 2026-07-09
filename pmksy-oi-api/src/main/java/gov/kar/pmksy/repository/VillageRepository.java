package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstAdministrativeVillage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageRepository
        extends JpaRepository<MstAdministrativeVillage, Long> {

    List<MstAdministrativeVillage>
    findByHobliHobliId(Long hobliId);
}