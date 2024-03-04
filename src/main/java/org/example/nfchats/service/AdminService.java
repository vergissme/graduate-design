package org.example.nfchats.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nfchats.common.JwtTokenUtils;
import org.example.nfchats.dao.AdminDao;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Params;
import org.example.nfchats.exception.CustomException;
import org.example.nfchats.exception.GlobalExceptionHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public  List<Admin> findAll() {
        return adminDao.getAdmin();
    }


    public PageInfo<Admin> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Admin> list=adminDao.findBySearch(params);
        return PageInfo.of(list);
    }
    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
    public void add(Admin admin) {
        // 1. 用户名一定要有，否则不让新增（后面需要用户名登录）
        if (admin.getUsername() == null || "".equals(admin.getUsername())) {
            throw new CustomException("用户名不能为空");
        }
        // 2. 进行重复性判断，同一名字的管理员不允许重复新增：只要根据用户名去数据库查询一下就可以了
        Admin user = adminDao.findByName(admin.getUsername());
        if (user != null) {
            // 说明已经有了，这里我们就要提示前台不允许新增了
            throw new CustomException("该用户名已存在，请更换用户名");
        }
        // 初始化一个密码
        if (admin.getPassword() == null) {
            admin.setPassword("123456");
            admin.setUser_type("管理员");
        }
        adminDao.insertSelective(admin);
    }

    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);
    }


    public void delete(Integer user_id) {
        adminDao.deleteByPrimaryKey(user_id);
    }


    public Admin login(Admin admin){
        // 1. 进行一些非空判断
        if (admin.getUsername() == null || "".equals(admin.getUsername())) {

            throw new CustomException("用户名不能为空");
        }
        if (admin.getPassword() == null || "".equals(admin.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        // 2. 从数据库里面根据这个用户名和密码去查询对应的管理员信息，
        Admin user = adminDao.findByNameAndPassword(admin.getUsername(), admin.getPassword());
        if (user == null) {
            // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
            throw new CustomException("用户名或密码输入错误");
        }
//         如果查出来了有，那说明确实有这个管理员，而且输入的用户名和密码都对；
//         生成该登录用户对应的token，然后跟着user一起返回到前台
        String token = JwtTokenUtils.genToken(user.getUser_id().toString(), user.getPassword());
        user.setToken(token);
        return user;
    }
}
