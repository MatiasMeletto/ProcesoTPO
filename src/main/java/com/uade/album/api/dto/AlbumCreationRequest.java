package com.uade.album.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class AlbumCreationRequest {
    private String name;
    private List<StickerCreationRequest> stickers;
    private String description;
}