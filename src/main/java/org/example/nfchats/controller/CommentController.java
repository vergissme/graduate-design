package org.example.nfchats.controller;

import com.github.pagehelper.PageInfo;
import org.example.nfchats.common.Result;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Comment;
import org.example.nfchats.entity.Params;
import org.example.nfchats.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping
    public Result save(@RequestBody Comment comment){
        commentService.update(comment);
        return Result.success();
    }
    @GetMapping("/findAll")
    public Result findAll() {
        List<Comment> list=commentService.findAll();
        return Result.success(list);
    }
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Comment> info = commentService.findBySearch(params);
        return Result.success(info);
    }
    @DeleteMapping("/{comment_id}")
    public Result delete(@PathVariable Integer comment_id){
        commentService.delete(comment_id);
        return Result.success();
    }
}
