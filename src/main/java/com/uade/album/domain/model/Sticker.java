package com.uade.album.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stickers")
@Data
@NoArgsConstructor
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int numero;
    private String imagenUrl;

    @Enumerated(EnumType.STRING) 
    private TipoRareza rareza; 
    
    private int stockTotal;
    private int stockDisponible;
    
    private String seccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    @JsonBackReference
    private Album album;
}