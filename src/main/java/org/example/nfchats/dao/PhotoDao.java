package org.example.nfchats.dao;

import org.apache.ibatis.annotations.Param;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Params;
import org.example.nfchats.entity.Photo;
import org.example.nfchats.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PhotoDao extends Mapper<Photo> {

    List<Photo> findAll() ;
    List<Photo> findBySearch(@Param("params") Params params);

    List<Photo> findBySearchFriends(@Param("params")Params params);

    List<Photo> findBySearchMine(@Param("params")Params params);
    List<Photo> findBySearchPets(@Param("params")Params params);
    List<Photo> findBySearchPerson(@Param("params")Params params);
    List<Photo> findBySearchPlaces(@Param("params")Params params);
}
