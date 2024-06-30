package com.squarecross.photoalbum.repository;

import com.squarecross.photoalbum.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository  extends JpaRepository<Photo, Long> {
    //특정 앨범에 속해있는 사진의 개수
    int countByAlbum_AlbumId(Long AlbumId);
    int countByAlbum_AlbumName(String AlbumName);
}
