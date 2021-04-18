package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
//        value = {"createdAt", "updatedAt", "id", "lastModifiedBy", "createdBy"},
        allowGetters = true
)
//@Where(clause = "delete_at = NULL")
public abstract class AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @CreatedBy
    private User createdBy;

    @JsonIgnore
    @ManyToOne
    @LastModifiedBy
    private User updatedBy;

//    @JsonIgnore
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "delete_at")
//    private Date deleteAt;

//    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private Date createdAt;

//    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Date getDeletedAt() {
//        return deleteAt;
//    }
//
//    public void setDeletedAt(Date deleteAt) {
//        this.deleteAt = deleteAt;
//    }
}