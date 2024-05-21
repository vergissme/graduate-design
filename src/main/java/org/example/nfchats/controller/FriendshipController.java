package org.example.nfchats.controller;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.example.nfchats.common.AutoLog;
import org.example.nfchats.common.Result;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.Friendship;
import org.example.nfchats.service.FriendshipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/friendship")
public class FriendshipController {
    @Resource
    private FriendshipService friendshipService;
    @GetMapping("/getfriend")
    public Result getFriendship(Params params) {
        PageInfo<Admin> info = friendshipService.getFriendship(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody Params params){
        System.out.println(params.getUsername());
        friendshipService.add(params);
        return Result.success();
    }



    @PostMapping("/del")
    public Result delete(@RequestBody Params params){
       friendshipService.delete(params);
        System.out.println(params.getUser_id());
        System.out.println(params.getFriendshipId());
        return Result.success();
    }
}
