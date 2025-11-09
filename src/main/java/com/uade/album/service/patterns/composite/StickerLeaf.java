package com.uade.album.service.patterns.composite;

import com.uade.album.domain.model.Sticker;
import java.util.Collections;
import java.util.List;

public class StickerLeaf implements AlbumComponent {

    private Sticker sticker;

    public StickerLeaf(Sticker sticker) {
        this.sticker = sticker;
    }

    @Override
    public String getNombre() {
        return sticker.getNombre();
    }

    @Override
    public int getTotalStickers() {
        return 1; 
    }

    @Override
    public String getTipo() {
        return "STICKER";
    }

    @Override
    public List<AlbumComponent> getChildren() {
        return Collections.emptyList(); // No tiene hijos
    }

    public Sticker getStickerData() {
        return sticker;
    }
}