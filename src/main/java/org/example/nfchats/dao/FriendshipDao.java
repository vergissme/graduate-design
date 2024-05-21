package org.example.nfchats.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.Friendship;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface FriendshipDao extends Mapper<Friendship> {

    List<Admin> getFriendship(@Param("params") Params params) ;



    void addFriend(@Param("user_id")Integer user_id, @Param("userid2")Integer userid2);


    void delFriends(@Param("params")Params params);

    Integer getuserid(@Param("username")String username);

    Integer checkFriendship(@Param("user_id")Integer user_id, @Param("userid2")Integer userid2);
}
