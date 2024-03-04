package org.example.nfchats.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "photo_id")
    private Integer photo_id;
    @Column(name = "content")
    private  String content;
    @Column(name = "upload_time")
    private Timestamp upload_time;
    @Column(name = "privacy_level")
    private  String privacy_level;
    @Column(name = "username")
    private  String username;
    public Comment() {
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Timestamp time) {
        this.upload_time = time;
    }

    public String getPrivacy_level() {
        return privacy_level;
    }

    public void setPrivacy_level(String privacy_level) {
        this.privacy_level = privacy_level;
    }

    public String getSpecific_id() {
        return specific_id;
    }

    public void setSpecific_id(String specific_id) {
        this.specific_id = specific_id;
    }

    @Column(name = "specific_id")
    private  String specific_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
