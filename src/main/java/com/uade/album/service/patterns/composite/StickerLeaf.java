package com.uade.album.service.patterns.composite;

import com.uade.album.domain.model.Sticker;
import lombok.Data;
import java.util.Collections;
import java.util.List;

// Representa la parte individual (la figurita).
@Data
public class StickerLeaf implements CompositeComponent {

    private final Sticker sticker;

    public StickerLeaf(Sticker sticker) {
        this.sticker = sticker;
    }

    @Override
    public String getName() {
        return sticker.getName();
    }

    @Override
    public Long getId() {
        return sticker.getId();
    }
  //a revisar
    // Los métodos para manipular hijos están vacíos, ya que es una hoja.
    @Override
    public void add(CompositeComponent component) {
        // No aplica a una hoja
    }

    @Override
    public void remove(CompositeComponent component) {
        // No aplica a una hoja
    }

    @Override
    public List<CompositeComponent> getChildren() {
        return Collections.emptyList();
    }

    // Método propio del diagrama
    public Long getStickerId() {
        return sticker.getId();
    }
    
  
    // Método 'addAlbumComponent' del diagrama (no tiene sentido en la hoja, pero se respeta)
    public void addAlbumComponent(CompositeComponent component) { 
        // No aplica a una hoja
    }
}