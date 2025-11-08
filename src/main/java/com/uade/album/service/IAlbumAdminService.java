package com.uade.album.service;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.service.patterns.composite.CompositeComponent;

public interface IAlbumAdminService {
    CompositeComponent createAlbum(AlbumCreationRequest request);
    CompositeComponent createSticker(StickerCreationRequest request);
    CompositeComponent getAlbumStructure(Long albumId);
    String generateStickersReport(String competition);
}