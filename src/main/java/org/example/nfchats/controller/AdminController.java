package org.example.nfchats.controller;

import com.github.pagehelper.PageInfo;
import com.wf.captcha.utils.CaptchaUtil;
import org.example.nfchats.common.Result;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Params;
import org.example.nfchats.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
/*
* controller的一个方法，就是网站的一个接口
* 可以加url
* 也可以指定请求方式 POST PUT GET DELETE
* @return
* */

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Resource
    private AdminService adminService;

    @PostMapping
    public Result save(@RequestBody Admin admin){
        if (admin.getUser_id()==null){
            adminService.add(admin);
        }else {
            adminService.update(admin);
        }

        return Result.success();
    }
    @GetMapping
    public Result findAll() {
        List<Admin> list=adminService.findAll();
        return Result.success(list);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        // 判断验证码对不对
//        if (!admin.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
//            // 如果不相等，说明验证不通过
//            CaptchaUtil.clear(request);
//            return Result.error("验证码不正确");
//        }
        Admin loginUser = adminService.login(admin);
       // CaptureConfig.CAPTURE_MAP.remove(key);
        return Result.success(loginUser);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        log.info("拦截器已放行，正式调用接口内部，查询管理员信息");
        PageInfo<Admin> info = adminService.findBySearch(params);

        return Result.success(info);
    }
    @DeleteMapping("/{user_id}")
    public Result delete(@PathVariable Integer user_id){
        adminService.delete(user_id);
        return Result.success();
    }
}
