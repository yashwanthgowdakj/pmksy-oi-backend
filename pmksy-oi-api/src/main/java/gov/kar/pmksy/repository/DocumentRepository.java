package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.TrnApplicationDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository
        extends JpaRepository<TrnApplicationDocument, Long> {

    List<TrnApplicationDocument>
    findByApplicationApplicationId(
            Long applicationId);
}