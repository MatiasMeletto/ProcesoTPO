// Ruta: com/uade/album/service/patterns/strategy/UniformRarityStrategy.java
package com.uade.album.service.patterns.strategy;

import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.TipoRareza;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component("uniformStrategy") 
public class AsignacionUniforme implements IRarezaAsignacionStrategy {

    private static final int STOCK_EPIC = 100;
    private static final int STOCK_RARE = 150;
    private static final int STOCK_COMMON = 200;
    
    private final Random rand = new Random();

    @Override
    public AssignmentResult assignRarityAndStock(StickerCreationRequest request) {
        // Genera un número aleatorio: 0, 1, o 2
        int chance = rand.nextInt(3); 

        switch (chance) {
            case 0:
                return new AssignmentResult(TipoRareza.EPICA, STOCK_EPIC);
            case 1:
                return new AssignmentResult(TipoRareza.RARA, STOCK_RARE);   
            default:
                return new AssignmentResult(TipoRareza.COMUN, STOCK_COMMON);
        }
    }
}