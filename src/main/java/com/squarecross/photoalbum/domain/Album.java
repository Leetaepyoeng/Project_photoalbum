package com.squarecross.photoalbum.domain;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//빌드시 스키마를 찾아서 album 테이블을 생성해줌
@Entity
@Table(name="album", schema="photo_album", uniqueConstraints = {@UniqueConstraint(columnNames = "album_id")})
public class Album {

    @Id
    // @id 값을 새롭게 부여할 때 사용하는 방법에 대한 정보를 입력
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", unique = true, nullable = false)
    private Long albumId;

    @Column(name = "album_name", unique = false, nullable = false)
    private String albumName;

    @Column(name="created_at", unique = false, nullable = true)
    // 새로운 앨범을 생성해 DB INSERT할 때 자동으로 현재 시간을 입력
    @CreationTimestamp
    private Date createdAt;

    // 이건 컬럼이 아님, mappedBy는 FK의 주인이 아닐 경우 사용(Photo 엔티티에 있는 album 객체랑 연결)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = CascadeType.ALL)
    private List<Photo> photos;

    //생성자
    public Album(){};

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
