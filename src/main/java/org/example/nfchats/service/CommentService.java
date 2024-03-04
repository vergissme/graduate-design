package org.example.nfchats.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nfchats.dao.CommentDao;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Comment;
import org.example.nfchats.entity.Params;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentDao commentDao;

    public  List<Comment> findAll() {
        return commentDao.findAll();
    }


    public PageInfo<Comment> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Comment> list=commentDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer commentId) {
        commentDao.deleteByPrimaryKey(commentId);
    }
    public void add(Comment comment){ commentDao.insertSelective(comment);}
    public void update(Comment comment) {
        commentDao.updateByPrimaryKeySelective(comment);
    }
}
