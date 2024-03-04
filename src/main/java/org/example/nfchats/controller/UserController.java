package org.example.nfchats.controller;

import com.github.pagehelper.PageInfo;
import org.example.nfchats.common.Result;
import org.example.nfchats.entity.User;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.User;
import org.example.nfchats.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
//    @GetMapping("/start")
//    public String start(){
//
//        return "欢迎！";
//    }
    @Resource
    private UserService userService;
    @GetMapping
    public Result getUser() {
        List<User> list=userService.getUser();
        return Result.success(list);
    }
    @PostMapping
    public Result save(@RequestBody User user){
        if (user.getUser_id()==null){
            userService.add(user);
        }else {
            userService.update(user);
        }

        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<User> info = userService.findBySearch(params);
        return Result.success(info);
    }
    @DeleteMapping("/{user_id}")
    public Result delete(@PathVariable Integer user_id){
        userService.delete(user_id);
        return Result.success();
    }
}
