package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.TrnOrchardInfrastructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrchardInfrastructureRepository
        extends JpaRepository<TrnOrchardInfrastructure, Long> {

    List<TrnOrchardInfrastructure>
    findByOrchardOrchardId(
            Long orchardId);
}