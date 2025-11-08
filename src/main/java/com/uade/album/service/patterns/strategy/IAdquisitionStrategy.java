package com.uade.album.service.patterns.strategy;

import com.uade.album.domain.model.Sticker;
import java.util.List;

public interface IAdquisitionStrategy {
    void applyAdquisitionLogic(List<Sticker> list);
}