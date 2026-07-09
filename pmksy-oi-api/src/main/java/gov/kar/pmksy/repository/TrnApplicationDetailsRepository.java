package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.TrnApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrnApplicationDetailsRepository
        extends JpaRepository<TrnApplicationDetails, Long> {
}