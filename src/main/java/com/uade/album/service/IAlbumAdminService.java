package com.uade.album.service;

import java.util.List;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.Album;
import com.uade.album.service.patterns.composite.SectionComposite;

public interface IAlbumAdminService {
    Album createAlbum(AlbumCreationRequest request);
    Album addStickersToAlbum(Long albumId, List<StickerCreationRequest> request);
    SectionComposite getAlbumStructure(Long albumId);
    Album publishAlbum(Long albumId);
}