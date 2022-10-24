package com.guoguoyun.web.controller.app.controller;


import cn.hutool.core.util.ObjectUtil;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.params.app.UserCollectParams;
import com.guoguoyun.system.response.AlbumByIdResponse;
import com.guoguoyun.web.controller.app.service.IndexService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/index")
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;


    /**
     * 欢迎广告
     * @return
     */
    @GetMapping("/indexAdv")
    public AjaxResult indexAdv(){
        return AjaxResult.success(indexService.indexAdv());
    }


    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public AjaxResult index(){
        return AjaxResult.success(indexService.indexSong());
    }


    /**
     * 专辑分类
     * @return
     */
    @GetMapping("/getAlbumClassify")
    public AjaxResult getAlbumClassify(Long id){
        return AjaxResult.success(indexService.getAlbumClassify(id));
    }


    /**
     * 所有专辑分类
     * @return
     */
    @GetMapping("/getAlbumClassifyList")
    public AjaxResult getAlbumClassifyList(){
        return AjaxResult.success(indexService.getAlbumClassifyList());
    }


    /**
     * 根据分类id获取专辑
     * @param album
     * @return
     */
    @GetMapping("/getAlbumByClassifyId")
    public TableDataInfo getAlbumByClassifyId(@RequestBody Album album,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "50") Integer pageSize){
        if (ObjectUtil.isEmpty(album.getAlbumClassifyId())){
            throw new SecurityException("请填写分类id");
        }
        startPage();
        List<Album> albumByClassifyId = indexService.getAlbumByClassifyId(album);
        return getDataTable(albumByClassifyId);
    }

    /**
     * 专辑信息
     * @param userCollectParams
     * @return
     */
    @GetMapping("/infoAlbum")
    public AjaxResult infoAlbum(UserCollectParams userCollectParams){
        AlbumByIdResponse album = indexService.infoAlbum(userCollectParams.getAlbumId(), userCollectParams.getUserId());
        return AjaxResult.success(album);

    }

    /**
     * 歌曲信息
     * @param userCollectParams
     * @return
     */
    @GetMapping("/infoSong")
    public AjaxResult infoSong(UserCollectParams userCollectParams){
        AlbumSong albumSong = indexService.infoSong(userCollectParams.getSongId(),userCollectParams.getUserId());
        return AjaxResult.success(albumSong);
    }
}
