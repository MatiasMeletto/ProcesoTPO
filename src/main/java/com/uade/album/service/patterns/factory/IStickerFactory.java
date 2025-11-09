package com.uade.album.service.patterns.factory;

import com.uade.album.domain.model.Album;
import com.uade.album.domain.model.Sticker;
import com.uade.album.api.dto.StickerCreationRequest;
import java.util.List;

public interface IStickerFactory {
    List<Sticker> createStickerSet(Album album, List<StickerCreationRequest> requests);
    List<Sticker> createPack(Album album); // por ejemplo, si se quiere agregar funcionalidad de packs
}