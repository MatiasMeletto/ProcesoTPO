package com.uade.album.service.patterns.composite;

import java.util.ArrayList;
import java.util.List;


public class SectionComposite implements AlbumComponent {

    private String nombre;
    private List<AlbumComponent> children = new ArrayList<>();

    public SectionComposite(String nombre) {
        this.nombre = nombre;
    }

    public void add(AlbumComponent component) {
        children.add(component);
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public int getTotalStickers() {
        return children.stream().mapToInt(AlbumComponent::getTotalStickers).sum();
    }

    @Override
    public String getTipo() {
        return "SECCION";
    }

    @Override
    public List<AlbumComponent> getChildren() {
        return this.children;
    }
}