package com.uade.album.api;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.service.IAlbumAdminService;
import com.uade.album.service.patterns.composite.CompositeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AlbumAdminController {

    @Autowired
    private IAlbumAdminService albumAdminService;

    @PostMapping("/albums")
    public ResponseEntity<CompositeComponent> createAlbum(@RequestBody AlbumCreationRequest request) {
        CompositeComponent album = albumAdminService.createAlbum(request);
        return ResponseEntity.ok(album);
    }

    @PostMapping("/stickers")
    public ResponseEntity<CompositeComponent> createSticker(@RequestBody StickerCreationRequest request) {
        CompositeComponent sticker = albumAdminService.createSticker(request);
        return ResponseEntity.ok(sticker);
    }

    @GetMapping("/albums/{albumId}/structure")
    public ResponseEntity<CompositeComponent> getAlbumStructure(@PathVariable Long albumId) {
        CompositeComponent structure = albumAdminService.getAlbumStructure(albumId);
        return ResponseEntity.ok(structure);
    }

    @GetMapping("/reports/stickers")
    public ResponseEntity<String> generateStickersReport(@RequestParam String competition) {
        String report = albumAdminService.generateStickersReport(competition);
        return ResponseEntity.ok(report);
    }
}