package org.example.nfchats.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name="photo")
public class Photo {

    @Column(name = "user_id")
    private Integer user_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer photo_id;
    @Column(name = "title")
    private  String title;
    @Column(name = "path")
    private  String path;
    @Column(name = "upload_time")
    private Timestamp upload_time;
    @Column(name = "username")
    private  String username;
    @Column(name = "type")
    private  String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Timestamp upload_time) {
        this.upload_time = upload_time;
    }

    public String getPrivacy_level() {
        return privacy_level;
    }

    public void setPrivacy_level(String privacy_level) {
        this.privacy_level = privacy_level;
    }


    public Photo() {
    }

    @Column(name = "privacy_level")
    private  String privacy_level;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
