package com.uade.album.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StickerUpdateRequest {

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String imagenUrl;
    
    private String seccion;
}