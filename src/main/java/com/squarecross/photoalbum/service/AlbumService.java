package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import org.springframework.stereotype.Service;
import com.squarecross.photoalbum.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public Album getAlbum(Long albumId){
        // Optional은 자바 8에서 도입된 클래스이며, 값이 존재할 수도 있고 존재하지 않을 수도 있는 컨테이너 역할
        Optional<Album> res = albumRepository.findById(albumId);
        // 앨범이 존재하는지
        if(res.isPresent()){
            return res.get();
        } else{
           throw new EntityNotFoundException(String.format("앨범 아이디가 %d로 조회되지 않습니다.", albumId));
        }
    }

}
