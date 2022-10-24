package com.guoguoyun.web.controller.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.core.page.TableDataInfo;
import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.domain.AlbumSong;
import com.guoguoyun.system.domain.UserCollectSongFolder;
import com.guoguoyun.system.params.app.UserCollectParams;
import com.guoguoyun.web.controller.app.service.AppCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/app/UserCollectAlbumOrArticle")
public class UserCollectAlbumOrArticleController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AppCollectService appCollectService;

    /**
     * 添加/取消收藏
     * @param userCollectParams
     * @return
     */
    @PostMapping("/userAddCollect")
    public AjaxResult userAddCollect(@RequestBody UserCollectParams userCollectParams){
        Long userId = tokenService.getLoginUser().getUserId();
        userCollectParams.setUserId(userId);
        Integer integer = appCollectService.userAddCollect(userCollectParams);
        return AjaxResult.success(integer);
    }

    /**
     * 乐曲收藏夹
     * @return
     */
    @GetMapping("/songFolderList")
    public TableDataInfo songFolderList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        Long userId = tokenService.getLoginUser().getUserId();
        Page<UserCollectSongFolder> userCollectSongFolderPage = appCollectService.songFolderList(userId, pageNum, pageSize);
        return getDataTable(userCollectSongFolderPage.getRecords(),userCollectSongFolderPage.getTotal());
    }

    /**
     * 专辑或文章收藏
     * @param userCollectParams
     * @return
     */
    @GetMapping("/userCollectList")
    public TableDataInfo userCollectList(UserCollectParams userCollectParams){
        Long userId = tokenService.getLoginUser().getUserId();
        userCollectParams.setUserId(userId);
        startPage();
        PageInfo pageInfo = appCollectService.userCollectList(userCollectParams);
        return getDataTable(pageInfo);
    }

    /**
     * 歌曲收藏夹详情
     * @param id
     * @return
     */
    @GetMapping("/songFolderDetail/{id}")
    public AjaxResult songFolderDetail(@PathVariable Long id){
        List<AlbumSong> albumSongs = appCollectService.songFolderDetail(id);
        return AjaxResult.success(albumSongs);
    }
}
