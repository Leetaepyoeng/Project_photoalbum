package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.domain.Photo;
import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.squarecross.photoalbum.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumServiceTest {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    AlbumService albumService;


    @Test
    void getAlbum() {

        //Mock 앨범을 생성해서 DB에 저장한다 (실제 반영은 안됨)
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        //방금 저장한 앨범 ID로 데이터가 조회되는지 확인
        AlbumDto resAlbum = albumService.getAlbum(savedAlbum.getAlbumId());
        // getAlbumName의 값이 "테스트" 가 맞는지 확인
        assertEquals("테스트", resAlbum.getAlbumName());

        // 네임명으로 데이터가 조회되는지 확인
        AlbumDto resAlbum2 = albumService.getAlbum(savedAlbum.getAlbumName());
        assertEquals("테스트", resAlbum2.getAlbumName());
    }

    @Test
    void testPhotoCount(){
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        //사진을 생성하고, setAlbum을 통해 앨범을 지정해준 이후, repository에 사진을 저장
        Photo photo1 = new Photo();
        photo1.setFileName("사진1");
        photo1.setAlbum(savedAlbum);
        photoRepository.save(photo1);

        Photo photo2 = new Photo();
        photo2.setFileName("2");
        photo2.setAlbum(savedAlbum);
        photoRepository.save(photo2);

        //방금 저장한 앨범 ID로 데이터가 조회되는지 확인
        AlbumDto resAlbum = albumService.getAlbum(savedAlbum.getAlbumId());
        Album reloadedAlbum = albumRepository.findById(savedAlbum.getAlbumId()).orElseThrow(() -> new RuntimeException("Album not found"));
        System.out.println("=========================================================");
        System.out.println("사진의 개수: "+resAlbum.getCount());
        System.out.println("=========================================================");
    }
}