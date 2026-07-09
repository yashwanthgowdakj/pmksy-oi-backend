package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository
        extends JpaRepository<MstGender, Long> {
}