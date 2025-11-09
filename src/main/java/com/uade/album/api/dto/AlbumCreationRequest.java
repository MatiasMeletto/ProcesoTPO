package com.uade.album.api.dto;

import lombok.Data;
import java.util.List;

import com.uade.album.domain.model.Dificultad;

import jakarta.validation.constraints.NotBlank;

@Data
public class AlbumCreationRequest {
    @NotBlank
    private String titulo;
    @NotBlank   
    private String categoria;
    private List<StickerCreationRequest> stickers;
    private String descripcion;
    @NotBlank()
    private Dificultad dificultad; 
}