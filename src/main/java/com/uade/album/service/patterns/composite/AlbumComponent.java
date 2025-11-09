package com.uade.album.service.patterns.composite;

import java.util.List;

public interface AlbumComponent {
    String getNombre();
    int getTotalStickers();
    String getTipo(); 

    List<AlbumComponent> getChildren();
}