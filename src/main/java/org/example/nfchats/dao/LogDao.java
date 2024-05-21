package org.example.nfchats.dao;

import org.apache.ibatis.annotations.Select;
import org.example.nfchats.entity.Log;
import org.example.nfchats.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface LogDao extends Mapper<Log> {


    List<Log> findBySearch(@Param("params") Params params);
}
