package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.TrnOrchardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrchardDetailsRepository
        extends JpaRepository<TrnOrchardDetails, Long> {

    List<TrnOrchardDetails>
    findByApplicationApplicationId(
            Long applicationId);
}