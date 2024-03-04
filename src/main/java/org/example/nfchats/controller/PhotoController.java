package org.example.nfchats.controller;

import com.github.pagehelper.PageInfo;
import org.example.nfchats.common.Result;
import org.example.nfchats.entity.Comment;
import org.example.nfchats.entity.Photo;
import org.example.nfchats.entity.Params;
import org.example.nfchats.service.PhotoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Resource
    private PhotoService photoService;
    @PostMapping
    public Result save(@RequestBody Photo photo){
        if (photo.getPhoto_id()==null){
            photoService.add(photo);
        }else {
            photoService.update(photo);
        }
        return Result.success();
    }
    @GetMapping("/findAll")
    public Result findAll() {
        List<Photo> list=photoService.findAll();
        return Result.success(list);
    }
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Photo> info = photoService.findBySearch(params);
        return Result.success(info);
    }
    @GetMapping("/searchFriends")
    public Result findBySearchFriends(Params params) {
        PageInfo<Photo> info = photoService.findBySearchFriends(params);
        return Result.success(info);
    }
    @GetMapping("/searchMine")
    public Result findBySearchMine(Params params) {
        PageInfo<Photo> info = photoService.findBySearchMine(params);
        return Result.success(info);
    }
    @GetMapping("/searchPets")
    public Result findBySearchPets(Params params) {
        PageInfo<Photo> info = photoService.findBySearchPets(params);
        return Result.success(info);
    }
    @GetMapping("/searchPerson")
    public Result findBySearchPerson(Params params) {
        PageInfo<Photo> info = photoService.findBySearchPerson(params);
        return Result.success(info);
    }
    @GetMapping("/searchPlaces")
    public Result findBySearchPlaces(Params params) {
        PageInfo<Photo> info = photoService.findBySearchPlaces(params);
        return Result.success(info);
    }
    @DeleteMapping("/{photo_id}")
    public Result delete(@PathVariable Integer photo_id){
        photoService.delete(photo_id);
        return Result.success();
    }
}
