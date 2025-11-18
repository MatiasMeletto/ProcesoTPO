package com.uade.album.service.patterns.strategy;

import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.TipoRareza;
import org.springframework.stereotype.Component;

@Component("presetStrategy")
public class AsignacionPredefinida implements IRarezaAsignacionStrategy {
    private static final int DEFAULT_STOCK = 100;

    @Override
    public AssignmentResult assignRarityAndStock(StickerCreationRequest request) {
        
        TipoRareza rareza;
        int stock;

        if (request.getRareza() != null && !request.getRareza().toString().isBlank()) {
            try {
                rareza = TipoRareza.valueOf(request.getRareza().toString().toUpperCase());
            } catch (IllegalArgumentException e) {
                rareza = TipoRareza.COMUN;
            }
        } else {
            rareza = TipoRareza.COMUN;
        }

        if (request.getStockTotal() != null && request.getStockTotal() > 0) {
            stock = request.getStockTotal();
        } else {
            stock = DEFAULT_STOCK;
        }

        return new AssignmentResult(rareza, stock);
    }
}