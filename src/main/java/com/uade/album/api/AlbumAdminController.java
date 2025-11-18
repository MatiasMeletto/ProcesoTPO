package com.uade.album.api;

import com.uade.album.api.dto.AlbumCreationRequest;
import com.uade.album.api.dto.AlbumUpdateRequest;
import com.uade.album.api.dto.StickerCreationRequest;
import com.uade.album.api.dto.StickerUpdateRequest;
import com.uade.album.domain.model.Album;
import com.uade.album.domain.model.Sticker;
import com.uade.album.service.IAlbumAdminService;
import com.uade.album.service.patterns.composite.SectionComposite;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin") 
public class AlbumAdminController {

    private final IAlbumAdminService albumAdminService;

    @Autowired
    public AlbumAdminController(IAlbumAdminService albumAdminService) {
        this.albumAdminService = albumAdminService;
    }

    @PostMapping("/albums")
    public ResponseEntity<Album> createAlbum(@Valid @RequestBody AlbumCreationRequest request) {
        Album createdAlbum = albumAdminService.createAlbum(request);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @GetMapping("/albums") 
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumAdminService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/albums/{id}") 
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long albumId) {
        Album album = albumAdminService.getAlbumById(albumId);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable("id") Long albumId, 
                                             @Valid @RequestBody AlbumUpdateRequest request) {
        Album updatedAlbum = albumAdminService.updateAlbum(albumId, request);
        return ResponseEntity.ok(updatedAlbum);
    }

    @DeleteMapping("/albums/{id}") 
    public ResponseEntity<Void> deleteAlbum(@PathVariable("id") Long albumId) {
        albumAdminService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/albums/{id}/publish")
    public ResponseEntity<Album> publishAlbum(@PathVariable("id") Long albumId) {
        Album publishedAlbum = albumAdminService.publishAlbum(albumId);
        return ResponseEntity.ok(publishedAlbum);
    }
    
    @GetMapping("/albums/{id}/structure")
    public ResponseEntity<SectionComposite> getAlbumStructure(@PathVariable("id") Long albumId) {
        SectionComposite structure = albumAdminService.getAlbumStructure(albumId);
        return ResponseEntity.ok(structure);
    }

    //
    // ENDPOINTS PARA STICKERS
    //

    @PostMapping("/albums/{id}/stickers")
    public ResponseEntity<Album> addStickers(@PathVariable("id") Long albumId, 
                                             @RequestBody List<StickerCreationRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            return ResponseEntity.badRequest().build(); 
        }
        Album updatedAlbum = albumAdminService.addStickersToAlbum(albumId, requests);
        return ResponseEntity.ok(updatedAlbum);
    }

    @PutMapping("/stickers/{id}") 
    public ResponseEntity<Sticker> updateSticker(@PathVariable("id") Long stickerId,
                                                 @Valid @RequestBody StickerUpdateRequest request) {
        Sticker updatedSticker = albumAdminService.updateSticker(stickerId, request);
        return ResponseEntity.ok(updatedSticker);
    }

    @DeleteMapping("/stickers/{id}")
    public ResponseEntity<Void> deleteSticker(@PathVariable("id") Long stickerId) {
        albumAdminService.deleteSticker(stickerId);
        return ResponseEntity.noContent().build();
    }
}