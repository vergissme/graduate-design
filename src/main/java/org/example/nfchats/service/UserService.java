package org.example.nfchats.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nfchats.dao.UserDao;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.User;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.User;
import org.example.nfchats.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    public PageInfo<User> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<User> list=userDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void add(User user) {
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            throw new CustomException("用户名不能为空");
        }
        User user2 = userDao.findByName(user.getUsername());
        if (user2 != null) {
            // 说明已经有了，这里我们就要提示前台不允许新增了
            throw new CustomException("该用户名已存在，请更换用户名");
        }
        //初始化密码
        if(user.getPassword()==null){
            user.setPassword("123456");
            user.setUser_type("普通用户");
        }
        userDao.insertSelective(user);
    }

    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }


    public void delete(Integer userId) {
        userDao.deleteByPrimaryKey(userId);
    }
    public  List<User> getUser() {
        return userDao.getUser();
    }

}
