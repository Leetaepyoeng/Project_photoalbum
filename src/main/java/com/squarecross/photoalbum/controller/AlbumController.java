package com.squarecross.photoalbum.controller;

import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/{albumId}", method = RequestMethod.GET)
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable("albumId") final long albumId) {
        AlbumDto albumDto = albumService.getAlbum(albumId);
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<AlbumDto> getAlbumByQuery(@RequestParam("albumId") final long albumId) {
        AlbumDto albumDto = albumService.getAlbum(albumId);
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @RequestMapping(value="/json_body", method = RequestMethod.POST)
    public ResponseEntity<AlbumDto> getAlbumByJson(@RequestBody final AlbumDto albumDto) {
        AlbumDto albumDto1 = albumService.getAlbum(albumDto.getAlbumId());
        return new ResponseEntity<>(albumDto1, HttpStatus.OK);
    }

}
