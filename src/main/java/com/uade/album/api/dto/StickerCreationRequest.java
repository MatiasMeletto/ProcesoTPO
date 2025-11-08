package com.uade.album.api.dto;

import com.uade.album.domain.model.Rarity;
import lombok.Data;

@Data
public class StickerCreationRequest {
    private String name;
    private Rarity rarity;
    
    private String imageUrl;
    private String description;
}