package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstAdministrativeHobli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobliRepository
        extends JpaRepository<MstAdministrativeHobli, Long> {

    List<MstAdministrativeHobli>
    findByTalukTalukId(Long talukId);
}