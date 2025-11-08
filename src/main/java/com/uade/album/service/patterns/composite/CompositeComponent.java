package com.uade.album.service.patterns.composite;

import java.util.List;

public interface CompositeComponent {
    String getName();
    Long getId();
    void add(CompositeComponent component);
    void remove(CompositeComponent component);
    List<CompositeComponent> getChildren();
}