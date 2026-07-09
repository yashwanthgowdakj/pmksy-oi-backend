package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_administrative_hobli")
public class MstAdministrativeHobli extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobli_id")
    private Long hobliId;

    @Column(name = "hobli_code")
    private String hobliCode;

    @Column(name = "hobli_name")
    private String hobliName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taluk_id")
    private MstAdministrativeTaluk taluk;
}