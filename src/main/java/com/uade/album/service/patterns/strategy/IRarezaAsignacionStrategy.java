package com.uade.album.service.patterns.strategy;

import com.uade.album.api.dto.StickerCreationRequest;

public interface IRarezaAsignacionStrategy {
    AssignmentResult assignRarityAndStock(StickerCreationRequest request);
}