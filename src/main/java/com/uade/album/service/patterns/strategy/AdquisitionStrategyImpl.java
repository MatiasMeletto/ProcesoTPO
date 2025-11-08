package com.uade.album.service.patterns.strategy;

import com.uade.album.domain.model.Rarity;
import com.uade.album.domain.model.Sticker;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;

@Component
public class AdquisitionStrategyImpl implements IAdquisitionStrategy {

    private static final double OPC_CHANCE = 0.5;
    private static final double RARE_CHANCE = 0.2;

    @Override
    public void applyAdquisitionLogic(List<Sticker> list) {
        applyRarity(list);
    }

    private void applyRarity(List<Sticker> stickers) {
        Random rand = new Random();
        for (Sticker sticker : stickers) {
            if (sticker.getRarity() == null) {
                double chance = rand.nextDouble(); 
                
                if (chance < RARE_CHANCE) { // 20% de probabilidad
                    sticker.setRarity(Rarity.EPICA);
                } else if (chance < OPC_CHANCE) { // 30% de probabilidad (0.5 - 0.2)
                    sticker.setRarity(Rarity.RARA);
                } else { // 50% de probabilidad
                    sticker.setRarity(Rarity.COMUN);
                }
            }
        }
    }
}