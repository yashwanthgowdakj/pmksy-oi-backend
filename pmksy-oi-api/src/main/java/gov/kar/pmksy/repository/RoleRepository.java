package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository
        extends JpaRepository<MstRole, Long> {
}