package org.example.nfchats.service;

import org.example.nfchats.dao.LogDao;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.entity.Log;
import org.example.nfchats.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LogService {

    @Resource
    private LogDao logDao;


    public void add(Log type) {
        logDao.insertSelective(type);
    }


    public PageInfo<Log> findBySearch(Params params) {

        // 开启分页查询
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Log> list = logDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void delete(Integer id) {
        logDao.deleteByPrimaryKey(id);
    }

}
