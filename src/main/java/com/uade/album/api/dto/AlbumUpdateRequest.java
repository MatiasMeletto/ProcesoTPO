package com.uade.album.api.dto;

import com.uade.album.domain.model.Dificultad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlbumUpdateRequest {

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 3, max = 50)
    private String titulo;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoria;

    private String descripcion;

    @NotBlank(message = "La dificultad no puede estar vacía")
    private Dificultad dificultad;
}