package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mst_gender")
public class MstGender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Long genderId;

    @Column(name = "gender_code")
    private String genderCode;

    @Column(name = "gender_name")
    private String genderName;

    @Column(name = "active_flag")
    private Boolean activeFlag;
}