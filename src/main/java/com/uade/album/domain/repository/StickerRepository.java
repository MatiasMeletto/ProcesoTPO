package com.uade.album.domain.repository;

import com.uade.album.domain.model.Sticker;
import com.uade.album.domain.model.TipoRareza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {
    List<Sticker> findByAlbumId(Long albumId);
    Optional<Sticker> findByAlbumIdAndNumeroAndRareza(Long albumId, int numero, TipoRareza rareza);
}