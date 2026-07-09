package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<MstUser, Long> {

    Optional<MstUser> findByUsername(
            String username);
}