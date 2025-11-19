package com.icheha.aprendia_api.exercises.layouts.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "box_layout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxLayoutEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_box_layout")
    private Long idBoxLayout;
    
    @Column(name = "id_layout", nullable = false)
    private Long idLayout;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_layout", insertable = false, updatable = false)
    private LayoutEntity layout;
    
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}




