package org.example.nfchats.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.nfchats.dao.PhotoDao;
import org.example.nfchats.entity.Comment;
import org.example.nfchats.entity.Photo;
import org.example.nfchats.entity.Params;
import org.example.nfchats.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PhotoService {
    @Resource
    private PhotoDao photoDao;

    public  List<Photo> findAll() {
        return photoDao.findAll();
    }


    public PageInfo<Photo> findBySearch(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Photo> list=photoDao.findBySearch(params);
        return PageInfo.of(list);
    }

    public void update(Photo photo) {
        photoDao.updateByPrimaryKeySelective(photo);
    }
    public void delete(Integer photo_id) {
        photoDao.deleteByPrimaryKey(photo_id);
    }

    public void add(Photo photo){

        photoDao.insertSelective(photo);}

    public PageInfo<Photo> findBySearchFriends(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Photo> list=photoDao.findBySearchFriends(params);
        return PageInfo.of(list);
    }
    public PageInfo<Photo> findBySearchMine(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Photo> list=photoDao.findBySearchMine(params);
        return PageInfo.of(list);
    }
    public PageInfo<Photo> findBySearchPets(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Photo> list=photoDao.findBySearchPets(params);
        return PageInfo.of(list);
    }

    public PageInfo<Photo> findBySearchPlaces(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Photo> list=photoDao.findBySearchPlaces(params);
        return PageInfo.of(list);
    }

    public PageInfo<Photo> findBySearchPerson(Params params) {
        PageHelper.startPage(params.getPageNume(), params.getPageSize());
        List<Photo> list=photoDao.findBySearchPerson(params);
        return PageInfo.of(list);
    }

}
