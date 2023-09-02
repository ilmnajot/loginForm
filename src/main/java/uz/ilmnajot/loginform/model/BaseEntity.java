package uz.ilmnajot.loginform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(EntityListeners.class)
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreatedBy
    @ManyToOne
    @JoinColumn(updatable = false)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(updatable = false)
    private User updatedBy;


    private Boolean deleted;
}
