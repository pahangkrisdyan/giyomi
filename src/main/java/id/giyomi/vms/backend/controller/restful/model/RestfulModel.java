package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.AuditModel;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.entity.User;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.persistence.*;
import java.util.Date;

public abstract class RestfulModel {
    private Long id;
    private Long createdBy;
    private Long updatedBy;
//    private Date deletedAt;
    private Date createdAt;
    private Date updatedAt;

    public RestfulModel(){

    }

    public RestfulModel(AuditModel auditModel) {
        this.id = auditModel.getId();
        this.createdAt = auditModel.getCreatedAt();
        this.updatedAt = auditModel.getUpdatedAt();
//        this.deletedAt = auditModel.getDeletedAt();
        if(auditModel.getCreatedBy()==null)
            this.createdBy = 0L;
        else
            this.createdBy = auditModel.getCreatedBy().getId();
        if(auditModel.getUpdatedBy()==null)
            this.updatedBy = 0L;
        else
            this.updatedBy = auditModel.getUpdatedBy().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

//    public Date getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(Date deletedAt) {
//        this.deletedAt = deletedAt;
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @JsonIgnore
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
