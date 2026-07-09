package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.TrnApplicationWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationWorkflowRepository
        extends JpaRepository<TrnApplicationWorkflow, Long> {

    List<TrnApplicationWorkflow> findByApplicationApplicationId(
            Long applicationId);
}