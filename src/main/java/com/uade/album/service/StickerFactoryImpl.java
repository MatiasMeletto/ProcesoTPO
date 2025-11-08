package com.uade.album.service;

import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.Sticker;
import com.uade.album.service.patterns.factory.StickerFactory;
import com.uade.album.service.patterns.strategy.IAdquisitionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StickerFactoryImpl implements StickerFactory {

    @Autowired
    private IAdquisitionStrategy adquisitionStrategy;

    @Override
    public Sticker createSticker(StickerCreationRequest request) {
        Sticker sticker = new Sticker();
        sticker.setName(request.getName());
        sticker.setRarity(request.getRarity()); 
        sticker.setUrl(request.getImageUrl());
        
        adquisitionStrategy.applyAdquisitionLogic(List.of(sticker));
        
        return sticker;
    }

    @Override
    public List<Sticker> createStickers(List<StickerCreationRequest> requests) {
        return requests.stream()
            .map(this::createSticker)
            .collect(Collectors.toList());
    }
}