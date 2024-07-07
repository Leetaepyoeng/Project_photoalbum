package com.squarecross.photoalbum.controller;

import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/{albumId}", method = RequestMethod.GET)
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable("albumId") final long albumId) {
        AlbumDto albumDto = albumService.getAlbum(albumId);
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
        //ResponseEntity는 HTTP 응답을 나타내는 객체로, 응답 본문, 상태 코드, 헤더 등을 포함할 수 있음
    }

    @GetMapping("/query")
    public ResponseEntity<AlbumDto> getAlbumByQuery(@RequestParam("albumId") final long albumId) {
        AlbumDto albumDto = albumService.getAlbum(albumId);
        return new ResponseEntity<>(albumDto, HttpStatus.OK);
    }

    @RequestMapping(value="/json_body", method = RequestMethod.POST)
    public ResponseEntity<AlbumDto> getAlbumByJson(@RequestBody final AlbumDto albumDto) {
        //@RequestBody 어노테이션은 Spring의 HttpMessageConverter를 이용하여 HTTP 요청 본문의 JSON 데이터를 AlbumDto 객체로 변환
        AlbumDto albumDto1 = albumService.getAlbum(albumDto.getAlbumId());
        return new ResponseEntity<>(albumDto1, HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.POST)
    public ResponseEntity<AlbumDto> createAlbum(@RequestBody final AlbumDto albumDto) throws IOException {
        AlbumDto savedAlbumDto = albumService.createAlbum(albumDto);
        return new ResponseEntity<>(savedAlbumDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{albumId}", method = RequestMethod.DELETE)
    public ResponseEntity<AlbumDto> deleteAlbum(@PathVariable Long albumId) throws IOException {
        albumService.deleteAlbumById(albumId);
        //NO_CONTENT는 요청이 성공적으로 처리되었지만 응답 본문이 없다는 것을 의미
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
