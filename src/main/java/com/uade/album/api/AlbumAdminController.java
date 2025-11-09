package com.uade.album.api;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.domain.model.Album;
import com.uade.album.service.IAlbumAdminService;
import com.uade.album.service.patterns.composite.SectionComposite;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/albums") 
public class AlbumAdminController {

    private final IAlbumAdminService albumAdminService;

    @Autowired
    public AlbumAdminController(IAlbumAdminService albumAdminService) {
        this.albumAdminService = albumAdminService;
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@Valid @RequestBody AlbumCreationRequest request) {
        Album createdAlbum = albumAdminService.createAlbum(request);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/stickers")
    public ResponseEntity<Album> addStickers(@PathVariable("id") Long albumId, 
                                             @RequestBody List<StickerCreationRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            return ResponseEntity.badRequest().build(); 
        }
        Album updatedAlbum = albumAdminService.addStickersToAlbum(albumId, requests);
        return ResponseEntity.ok(updatedAlbum);
    }

    @GetMapping("/{id}/structure")
    public ResponseEntity<SectionComposite> getAlbumStructure(@PathVariable("id") Long albumId) {
        SectionComposite structure = albumAdminService.getAlbumStructure(albumId);
        return ResponseEntity.ok(structure);
    }
    
    @PostMapping("/{id}/publish")
    public ResponseEntity<Album> publishAlbum(@PathVariable("id") Long albumId) {
        Album publishedAlbum = albumAdminService.publishAlbum(albumId);
        return ResponseEntity.ok(publishedAlbum);
    }
}