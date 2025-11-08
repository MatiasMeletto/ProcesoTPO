package com.uade.album.service.patterns.factory;

import com.uade.album.domain.model.Sticker;
import com.uade.album.api.dto.StickerCreationRequest;
import java.util.List;

public interface StickerFactory {
    Sticker createSticker(StickerCreationRequest request);
    List<Sticker> createStickers(List<StickerCreationRequest> requests);
}