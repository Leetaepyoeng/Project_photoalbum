package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.squarecross.photoalbum.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumServiceTest {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AlbumService albumService;


    @Test
    void getAlbum() {

        //Mock 앨범을 생성해서 DB에 저장한다 (실제 반영은 안됨)
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        //방금 저장한 앨범 ID로 데이터가 조회되는지 확인
        Album resAlbum = albumService.getAlbum(savedAlbum.getAlbumId());
        // getAlbumName의 값이 "테스트" 가 맞는지 확인
        assertEquals("테스트", resAlbum.getAlbumName());

        // 네임명으로 데이터가 조회되는지 확인
        Album resAlbum2 = albumService.getAlbum(savedAlbum.getAlbumName());
        assertEquals("테스트", resAlbum2.getAlbumName());
    }
}