package gov.kar.pmksy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trn_application_document")
public class TrnApplicationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private TrnFarmerApplication application;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_content", columnDefinition = "bytea")
    private byte[] fileContent;

    @Column(name = "uploaded_date")
    private LocalDateTime uploadedDate;

    @Column(name = "uploaded_by")
    private Long uploadedBy;

    @Column(name = "active_flag")
    private Boolean activeFlag;

    @Column(name = "taluk_verified")
    private Boolean talukVerified;

    @Column(name = "taluk_verified_by")
    private Long talukVerifiedBy;

    @Column(name = "taluk_verified_date")
    private LocalDateTime talukVerifiedDate;

    @Column(name = "taluk_remarks")
    private String talukRemarks;

    @Column(name = "district_verified")
    private Boolean districtVerified;

    @Column(name = "district_verified_by")
    private Long districtVerifiedBy;

    @Column(name = "district_verified_date")
    private LocalDateTime districtVerifiedDate;

    @Column(name = "district_remarks")
    private String districtRemarks;
}