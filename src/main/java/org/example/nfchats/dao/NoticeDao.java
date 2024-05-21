package org.example.nfchats.dao;

import org.example.nfchats.entity.Notice;
import org.example.nfchats.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface NoticeDao extends Mapper<Notice> {

    List<Notice> findBySearch(@Param("params") Params params);

    @Select("select * from notice order by time desc limit 4")
    List<Notice> findTop3();
}
