package org.example.nfchats.dao;

import org.apache.ibatis.annotations.Param;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Comment;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentDao extends Mapper<Comment> {

    List<Comment> findAll() ;
    List<Comment> findBySearch(@Param("params") Params params);
}
