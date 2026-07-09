package gov.kar.pmksy.repository;

import gov.kar.pmksy.entity.TrnFarmerApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository
        extends JpaRepository<TrnFarmerApplication, Long> {

    Optional<TrnFarmerApplication>
    findByApplicationNumber(
            String applicationNumber);

    boolean existsByApplicationNumber(
            String applicationNumber);

    List<TrnFarmerApplication>
    findByCurrentLevelAndTalukName(
            String currentLevel,
            String talukName);

    List<TrnFarmerApplication>
    findByCurrentLevelAndDistrictName(
            String currentLevel,
            String districtName);

    long countByCurrentLevelAndTalukName(
            String currentLevel,
            String talukName);

    long countByApplicationStatusAndTalukName(
            String applicationStatus,
            String talukName);

    long countByCurrentLevelAndApplicationStatusAndTalukName(
            String currentLevel,
            String applicationStatus,
            String talukName);

    long countByCurrentLevelAndDistrictName(
            String currentLevel,
            String districtName);

    long countByApplicationStatusAndDistrictName(
            String applicationStatus,
            String districtName);

    long countByCurrentLevelAndApplicationStatusAndDistrictName(
            String currentLevel,
            String applicationStatus,
            String districtName);

    // NEW
    List<TrnFarmerApplication> findByTalukNameOrderByApplicationDateDesc(
            String talukName);

    List<TrnFarmerApplication>
    findByApplicationStatusAndTalukName(
            String applicationStatus,
            String talukName);

    List<TrnFarmerApplication>
    findByCurrentLevelAndApplicationStatusAndTalukName(
            String currentLevel,
            String applicationStatus,
            String talukName);

    List<TrnFarmerApplication>
    findByApplicationStatusAndDistrictName(
            String applicationStatus,
            String districtName);

    List<TrnFarmerApplication>
    findByCurrentLevelAndApplicationStatusAndDistrictName(
            String currentLevel,
            String applicationStatus,
            String districtName);

}