package com.uade.album.api.dto;

import com.uade.album.domain.model.TipoRareza;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StickerCreationRequest {

    @NotBlank
    private String nombre;

    @Min(1)
    private int numero;
    private String seccion;

    private TipoRareza rareza;
    private Integer stockTotal;

    @NotBlank
    private String imagenUrl;
}