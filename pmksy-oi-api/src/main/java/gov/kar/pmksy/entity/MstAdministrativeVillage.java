package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_administrative_village")
public class MstAdministrativeVillage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_id")
    private Long villageId;

    @Column(name = "village_code")
    private String villageCode;

    @Column(name = "village_name")
    private String villageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hobli_id")
    private MstAdministrativeHobli hobli;
}