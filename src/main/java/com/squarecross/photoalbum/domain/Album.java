package com.squarecross.photoalbum.domain;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

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
}
