package gov.kar.pmksy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_administrative_taluk")
public class MstAdministrativeTaluk extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taluk_id")
    private Long talukId;

    @Column(name = "taluk_code")
    private String talukCode;

    @Column(name = "taluk_name")
    private String talukName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private MstAdministrativeDistrict district;
}