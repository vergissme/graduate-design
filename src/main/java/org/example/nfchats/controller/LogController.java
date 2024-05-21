package org.example.nfchats.controller;

import org.apache.ibatis.annotations.Param;
import org.example.nfchats.common.AutoLog;
import org.example.nfchats.common.Result;
import org.example.nfchats.entity.Log;
import org.example.nfchats.entity.Params;
import org.example.nfchats.service.LogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {

    @Resource
    @Autowired
    private LogService logService;


    @PostMapping
    public Result save(@RequestBody Log log) {
       logService.add(log);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Log> info = logService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    @AutoLog("删除日志")
    public Result delete(@PathVariable Integer id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        logService.delete(id);
        return Result.success();
    }

}
