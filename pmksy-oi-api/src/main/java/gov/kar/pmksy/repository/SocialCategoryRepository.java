package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstSocialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialCategoryRepository
        extends JpaRepository<MstSocialCategory, Long> {
}