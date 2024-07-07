package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.Constants;
import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.mapper.AlbumMapper;
import com.squarecross.photoalbum.repository.PhotoRepository;
import org.springframework.stereotype.Service;
import com.squarecross.photoalbum.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;

import com.squarecross.photoalbum.Constants;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public void deleteAlbumById(Long albumId) throws IOException {
        this.albumRepository.deleteById(albumId);
        this.deleteAlbumDirectories(albumId);
    }

    public AlbumDto createAlbum(AlbumDto albumDto) throws IOException {
        Album album = AlbumMapper.convertToModel(albumDto);
        this.albumRepository.save(album);
        this.createAlbumDirectories(album);
        return AlbumMapper.convertToDto(album);
    }

    private void deleteAlbumDirectories(Long albumId) throws IOException {
        Files.delete(Paths.get(Constants.PATH_PREFIX + "/photos/original/" + albumId));
        Files.delete(Paths.get(Constants.PATH_PREFIX + "/photos/thumb/" + albumId));
    }

    private void createAlbumDirectories(Album album) throws IOException {
        Files.createDirectories(Paths.get(Constants.PATH_PREFIX + "/photos/original/" + album.getAlbumId()));
        Files.createDirectories(Paths.get(Constants.PATH_PREFIX + "/photos/thumb/" + album.getAlbumId()));
    }

    public AlbumDto getAlbum(Long albumId){
        // Optional은 자바 8에서 도입된 클래스이며, 값이 존재할 수도 있고 존재하지 않을 수도 있는 컨테이너 역할
        Optional<Album> res = albumRepository.findById(albumId);
        // 앨범이 존재하는지
        if(res.isPresent()){
            AlbumDto albumDto = AlbumMapper.convertToDto(res.get());
            albumDto.setCount(photoRepository.countByAlbum_AlbumId(albumId));
            return albumDto;
        } else{
           throw new EntityNotFoundException(String.format("앨범 아이디가 %d로 조회되지 않습니다.", albumId));
        }
    }

    public AlbumDto getAlbum(String albumName){
        Optional<Album> res = albumRepository.findByAlbumName(albumName);// 앨범이 존재하는지
        if(res.isPresent()){
            AlbumDto albumDto = AlbumMapper.convertToDto(res.get());
            albumDto.setCount(photoRepository.countByAlbum_AlbumName(albumName));
            return albumDto;
        } else{
            throw new EntityNotFoundException(String.format("앨범 이름이 %s로 조회되지 않습니다.", albumName));
        }
    }

}
