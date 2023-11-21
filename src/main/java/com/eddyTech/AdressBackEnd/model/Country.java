package com.eddyTech.AdressBackEnd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false ,unique = true)
    private String name;

    @Column(name = "code", nullable = true, unique = true )
    private String code;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private Date updateAt;

    @PrePersist
    protected void prePersist(){
        if (this.createdAt == null) createdAt = new Date();
        if (this.updateAt == null) updateAt = new Date();
    }

    @PreUpdate
    protected void preUpdate(){ this.updateAt = new Date();}

}
