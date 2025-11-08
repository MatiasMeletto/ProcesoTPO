package com.uade.album.service;

import com.uade.album.domain.model.Album;
import com.uade.album.domain.model.Sticker;
import com.uade.album.domain.repository.AlbumRepository;
import com.uade.album.domain.repository.StickerRepository;
import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.service.patterns.composite.AlbumComposite;
import com.uade.album.service.patterns.composite.CompositeComponent;
import com.uade.album.service.patterns.composite.StickerLeaf;
import com.uade.album.service.patterns.factory.StickerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumAdminService implements IAlbumAdminService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private StickerFactory stickerFactory;

    @Override
    @Transactional
    public CompositeComponent createAlbum(AlbumCreationRequest request) {
        Album album = new Album();
        album.setName(request.getName());
        album.setDescription(request.getDescription());
        Album savedAlbum = albumRepository.save(album);
        Long albumId = savedAlbum.getId();

        List<Sticker> stickers = stickerFactory.createStickers(request.getStickers());
        
        for (Sticker sticker : stickers) {
            sticker.setAlbumId(albumId);
        }
        List<Sticker> savedStickers = stickerRepository.saveAll(stickers);

        AlbumComposite composite = new AlbumComposite(albumId, savedAlbum.getName());
        savedStickers.forEach(sticker -> 
            composite.add(new StickerLeaf(sticker))
        );
        
        return composite;
    }

    @Override
    @Transactional
    public CompositeComponent createSticker(StickerCreationRequest request) {
        Sticker sticker = stickerFactory.createSticker(request);
        Sticker savedSticker = stickerRepository.save(sticker);
        return new StickerLeaf(savedSticker);
    }

    @Override
    @Transactional(readOnly = true)
    public CompositeComponent getAlbumStructure(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album no encontrado"));

        List<Sticker> stickers = stickerRepository.findByAlbumId(albumId);

        AlbumComposite composite = new AlbumComposite(album.getId(), album.getName());
        stickers.forEach(sticker ->
                composite.add(new StickerLeaf(sticker))
        );
        
        return composite;
    }

    @Override
    public String generateStickersReport(String competition) {
        // Implementación genérica: dado que el atributo específico (PlayerType) fue
        // eliminado, esta función se limita a devolver un conteo total para
        // cumplir con la firma del diagrama.
        long totalStickers = stickerRepository.count();
        
        return "Reporte solicitado para la categoría '" + competition + "'. Total de figuritas en el sistema: " + totalStickers;
    }
}