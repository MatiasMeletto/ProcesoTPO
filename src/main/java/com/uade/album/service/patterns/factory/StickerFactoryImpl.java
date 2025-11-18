package com.uade.album.service.patterns.factory;

import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.Album;
import com.uade.album.domain.model.Dificultad;
import com.uade.album.domain.model.Sticker;
import com.uade.album.domain.repository.StickerRepository;
import com.uade.album.service.patterns.strategy.AssignmentResult; 
import com.uade.album.service.patterns.strategy.IRarezaAsignacionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import java.util.Optional; 

@Service("adminFactory") // por si se necesita otro tipo de factory
public class StickerFactoryImpl implements IStickerFactory {

    private final Map<String, IRarezaAsignacionStrategy> rarityStrategy;
    private final StickerRepository stickerRepository; 

    @Autowired
    public StickerFactoryImpl(Map<String, IRarezaAsignacionStrategy> rarityStrategy, StickerRepository stickerRepository) { 
        this.rarityStrategy = rarityStrategy;
        this.stickerRepository = stickerRepository; 
    }

    private IRarezaAsignacionStrategy selectStrategy(Album album) {
        Dificultad difficulty = album.getDificultad();

        if (Dificultad.FACIL.equals(difficulty)) {
            return rarityStrategy.get("uniformStrategy");
        } else if (Dificultad.MEDIO.equals(difficulty)) {
            return rarityStrategy.get("presetStrategy");
        } else  {
            return rarityStrategy.get("weightedStrategy");
        }
    }
    @Override
    public List<Sticker> createStickerSet(Album album, List<StickerCreationRequest> requests) {
        
        List<Sticker> stickersToSave = new ArrayList<>();

        IRarezaAsignacionStrategy strategy = selectStrategy(album);

        for (StickerCreationRequest req : requests) {
            validateRequest(req);

            AssignmentResult result = strategy.assignRarityAndStock(req);

            Optional<Sticker> existingSticker = stickerRepository.findByAlbumIdAndNumeroAndRareza(
                    album.getId(),
                    req.getNumero(),
                    result.rareza()
            );

            Sticker sticker;
            if (existingSticker.isPresent()) {
                sticker = existingSticker.get();
                sticker.setStockTotal(sticker.getStockTotal() + result.initialStock());
                sticker.setStockDisponible(sticker.getStockDisponible() + result.initialStock());


            } else {
                sticker = new Sticker();
                sticker.setAlbum(album);
                sticker.setNombre(req.getNombre());
                sticker.setNumero(req.getNumero());
                sticker.setImagenUrl(req.getImagenUrl());
                sticker.setSeccion(req.getSeccion());
                sticker.setRareza(result.rareza()); 
                sticker.setStockTotal(result.initialStock()); 
                sticker.setStockDisponible(result.initialStock());
            }
            
            stickersToSave.add(sticker);
        }

        return stickersToSave;
    }

    @Override
    public List<Sticker> createPack(Album album) {
        // Este método no es responsabilidad del Admin
        // Simplemente lanzamos una excepción para indicarlo
        throw new UnsupportedOperationException("La creación de paquetes debe ser manejada por la fábrica de la Tienda (PackStickerSetFactory)");
    }

    private void validateRequest(StickerCreationRequest req) {
        if (req.getImagenUrl() == null || req.getImagenUrl().isBlank()) {
            throw new IllegalArgumentException("La URL de la imagen es obligatoria.");
        }
    }
}