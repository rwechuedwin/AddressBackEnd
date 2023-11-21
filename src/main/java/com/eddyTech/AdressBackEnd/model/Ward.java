package com.eddyTech.AdressBackEnd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wards")
public class Ward {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne
        @JoinColumn(name = "district_id")
        private District district;

        @Column(nullable = false, name = "name")
        private String name;

        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false)
        private Date createdAt;

        @LastModifiedDate
        @Column(name = "updated_at")
        private Date updatedAt;

        @PrePersist
        protected void prePersist() {
            if (this.createdAt == null) createdAt = new Date();
            if (this.updatedAt == null) updatedAt = new Date();
        }


        @PreUpdate
        protected void preUpdate() {
            this.updatedAt = new Date();
        }

        @JsonCreator
        public Ward fromName(@JsonProperty("name") String name) {
            return new Ward(name);
        }

        public Ward(String name) {
            this.name = name;
        }


    }
