package com.uade.album.service.patterns.composite;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

// Representa la parte compuesta (el álbum). Contiene StickerLeafs o sub-AlbumComposites.
@Data
public class AlbumComposite implements CompositeComponent {

    private final Long id;
    private final String name;
    private final List<CompositeComponent> children = new ArrayList<>();

    public AlbumComposite(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void add(CompositeComponent component) {
        children.add(component);
    }

    @Override
    public void remove(CompositeComponent component) {
        children.remove(component);
    }

    @Override
    public List<CompositeComponent> getChildren() {
        return children;
    }

    // ???? podria ser inspeccionar creo
    // Método propio del diagrama
    public String getBarCode() {
        return "BARCODE-" + this.id; // Implementación de ejemplo
    }

    // Método 'addAlbumComponent' del diagrama
    public void addAlbumComponent(CompositeComponent component) {
        this.add(component);
    }
}