package com.uade.album.service;

import java.util.List;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.AlbumUpdateRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.api.dto.StickerUpdateRequest;
import com.uade.album.domain.model.Album;
import com.uade.album.domain.model.Sticker;
import com.uade.album.service.patterns.composite.SectionComposite;

public interface IAlbumAdminService {
    Album createAlbum(AlbumCreationRequest request);
    Album addStickersToAlbum(Long albumId, List<StickerCreationRequest> request);
    Album publishAlbum(Long albumId);

    List<Album> getAllAlbums(); 
    Album getAlbumById(Long albumId);
    SectionComposite getAlbumStructure(Long albumId);

    Album updateAlbum(Long albumId, AlbumUpdateRequest request);
    Sticker updateSticker(Long stickerId, StickerUpdateRequest request);

    void deleteAlbum(Long albumId);
    void deleteSticker(Long stickerId);
}