package com.uade.album.service;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.Album;
import com.uade.album.domain.model.Sticker;
import com.uade.album.domain.repository.AlbumRepository;
import com.uade.album.domain.repository.StickerRepository;
import com.uade.album.service.patterns.composite.SectionComposite;
import com.uade.album.service.patterns.composite.StickerLeaf;
import com.uade.album.service.patterns.factory.IStickerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlbumAdminService implements IAlbumAdminService {
    private final AlbumRepository albumRepository;
    private final StickerRepository stickerRepository;
    private final IStickerFactory stickerFactory;

    @Autowired
    public AlbumAdminService(AlbumRepository albumRepository,
                             StickerRepository stickerRepository,
                             @Qualifier("adminFactory") IStickerFactory stickerFactory) {
        this.albumRepository = albumRepository;
        this.stickerRepository = stickerRepository;
        this.stickerFactory = stickerFactory;
    }

    @Override
    public Album createAlbum(AlbumCreationRequest request) {
        Album newAlbum = new Album();
        newAlbum.setTitulo(request.getTitulo());
        newAlbum.setCategoria(request.getCategoria());
        newAlbum.setDescripcion(request.getDescripcion());
        newAlbum.setTotalFiguritas(0); // Inicia en 0
        newAlbum.setDificultad(request.getDificultad());
        newAlbum.setPublicado(false);
        return albumRepository.save(newAlbum);
    }

    @Override
    @Transactional
    public Album publishAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Álbum no encontrado con ID: " + albumId));

        if (album.isPublicado()) {
            throw new IllegalStateException("El álbum con ID: " + albumId + " ya fue publicado.");
        }

        if (album.getTotalFiguritas() < 10) {
            throw new IllegalStateException(
                "El álbum no puede publicarse con menos de 10 figuritas. " +
                "Total actual: " + album.getTotalFiguritas()
            );
        }
        album.setPublicado(true);
        return albumRepository.save(album);
    }

    @Override
    @Transactional 
    public Album addStickersToAlbum(Long albumId, List<StickerCreationRequest> stickerRequests) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Álbum no encontrado con ID: " + albumId));

        List<Sticker> newStickers = stickerFactory.createStickerSet(album, stickerRequests);

        stickerRepository.saveAll(newStickers);

        List<Sticker> totalStickers = stickerRepository.findByAlbumId(albumId);
        album.setTotalFiguritas(totalStickers.size());
        return albumRepository.save(album);
    }

    @Override
    public SectionComposite getAlbumStructure(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Álbum no encontrado"));

        List<Sticker> allStickers = stickerRepository.findByAlbumId(albumId);

        Map<String, List<Sticker>> stickersBySection = allStickers.stream()
                .collect(Collectors.groupingBy(
                        s -> (s.getSeccion() == null || s.getSeccion().isBlank()) ? "_RAIZ_" : s.getSeccion()
                ));

        SectionComposite albumRoot = new SectionComposite(album.getTitulo());

        for (Map.Entry<String, List<Sticker>> entry : stickersBySection.entrySet()) {
            String sectionName = entry.getKey();
            List<Sticker> stickersInGroup = entry.getValue();

            if (sectionName.equals("_RAIZ_")) {
                for (Sticker sticker : stickersInGroup) {
                    albumRoot.add(new StickerLeaf(sticker));
                }
            } else {
                SectionComposite sectionNode = new SectionComposite(sectionName);
                for (Sticker sticker : stickersInGroup) {
                    sectionNode.add(new StickerLeaf(sticker));
                }
                albumRoot.add(sectionNode);
            }
        }
        return albumRoot;
    }
}