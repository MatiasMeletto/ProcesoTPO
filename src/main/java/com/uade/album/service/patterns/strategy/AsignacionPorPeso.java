package com.uade.album.service.patterns.strategy;

import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.TipoRareza;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component("weightedStrategy")
public class AsignacionPorPeso implements IRarezaAsignacionStrategy {

    private static final double EPIC_CHANCE = 0.05;
    private static final double RARE_CHANCE = 0.25;
    private static final int STOCK_EPIC = 50;
    private static final int STOCK_RARE = 150;
    private static final int STOCK_COMMON = 500;
    
    private final Random rand = new Random(); // Es mejor tener una sola instancia

    @Override
    public AssignmentResult assignRarityAndStock(StickerCreationRequest request) {
        double chance = rand.nextDouble();

        if (chance < EPIC_CHANCE) {
            return new AssignmentResult(TipoRareza.EPICA, STOCK_EPIC);
        } else if (chance < RARE_CHANCE) {
            return new AssignmentResult(TipoRareza.RARA, STOCK_RARE);
        } else {
            return new AssignmentResult(TipoRareza.COMUN, STOCK_COMMON);
        }
    }
}