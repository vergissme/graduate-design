package org.example.nfchats.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.example.nfchats.dao.FriendshipDao;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.Friendship;
import org.example.nfchats.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendshipService {
    @Resource
    private FriendshipDao friendshipDao;

    public  PageInfo<Admin> getFriendship(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Admin> list=friendshipDao.getFriendship(params);
        return PageInfo.of(list);
    }

    public void add(Params params) {
        Integer userid2=friendshipDao.getuserid(params.getUsername());
        if(userid2==null){
            throw new CustomException("用户不存在");
        }
        if (friendshipDao.checkFriendship(params.getUser_id(),userid2)!= 0) {
            throw new CustomException("好友已添加");
        }
        friendshipDao.addFriend(params.getUser_id(),userid2);
    }

    public void delete(Params params) {
        friendshipDao.delFriends(params);
    }



}
