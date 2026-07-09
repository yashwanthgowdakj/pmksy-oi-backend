package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.MstFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<MstFarmer, Long> {

    Optional<MstFarmer> findByFidNumber(String fidNumber);

    boolean existsByFidNumber(String fidNumber);
}