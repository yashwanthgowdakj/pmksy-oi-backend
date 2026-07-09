package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_administrative_district")
public class MstAdministrativeDistrict extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district_name")
    private String districtName;
}