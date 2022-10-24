package com.guoguoyun.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.enums.CheckState;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.response.UserContributeResponse;
import com.guoguoyun.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.UserContributeMapper;

/**
 * 投稿Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-25
 */
@Service
public class UserContributeServiceImpl extends ServiceImpl<UserContributeMapper, UserContribute> implements IUserContributeService {
    @Autowired
    private UserContributeMapper userContributeMapper;

    @Autowired
    private IUserContributeAlbumService userContributeAlbumService;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IUserContributeRelationService userContributeRelationService;

    /**
     * 查询投稿
     *
     * @param id 投稿主键
     * @return 投稿
     */
    @Override
    public UserContributeResponse selectUserContributeById(Long id) {
        UserContribute userContribute = userContributeMapper.selectUserContributeById(id);
        UserContributeResponse oneUserContributeResponse = this.getOneUserContributeResponse(userContribute);
        List<ImgUrlVo> imgUrls = JSONArray.parseArray(oneUserContributeResponse.getImgUrls(), ImgUrlVo.class);
        oneUserContributeResponse.setImgUrlList(imgUrls);
        QueryWrapper<UserContributeAlbum> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserContributeAlbum::getContributeId,userContribute.getId());
        List<Long> albumIds = userContributeAlbumService.list(wrapper).stream().map(x -> x.getAlbumId()).collect(Collectors.toList());

        if (albumIds.size() != 0){
            QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
            albumQueryWrapper.lambda().in(Album::getId,albumIds);
            List<Album> albums = albumService.list(albumQueryWrapper);
            oneUserContributeResponse.setAlbums(albums);
        }
        return oneUserContributeResponse;
    }

    /**
     * 查询投稿列表
     *
     * @param userContribute 投稿
     * @return 投稿
     */
    @Override
    public PageInfo selectUserContributeList(UserContribute userContribute) {
        List<UserContribute> userContributes = userContributeMapper.selectUserContributeListLike(userContribute);
        List<UserContributeResponse> userContributeResponses = new ArrayList<>();

        for (UserContribute t :userContributes) {
            AppUser appUser = appUserService.getById(t.getUserId());
            if (ObjectUtil.isNotEmpty(appUser)){
                UserContributeResponse oneUserContributeResponse = this.getOneUserContributeResponse(t);
                oneUserContributeResponse.setCheckStateName(CheckState.getEnumType(t.getCheckState()).getInfo());
                QueryWrapper<UserContributeRelation> userContributeRelationQueryWrapper = new QueryWrapper<>();
                userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getContributeId,t.getId());
                userContributeRelationQueryWrapper.lambda().eq(UserContributeRelation::getType, MusicCode.USER_CONTRIBUTE_RELATION_READ);
                List<UserContributeRelation> userContributeRelations = userContributeRelationService.list(userContributeRelationQueryWrapper);
                oneUserContributeResponse.setReadNum(userContributeRelations.size());

                userContributeResponses.add(oneUserContributeResponse);
            }else {
                continue;
            }
        }

        PageInfo<UserContribute> page = new PageInfo<>(userContributes);
        PageInfo<UserContributeResponse> pageInfo = new PageInfo<>(userContributeResponses);
        BeanUtils.copyProperties(page, pageInfo);
        pageInfo.setList(userContributeResponses);
        return pageInfo;
    }

    /**
     * 新增投稿
     *
     * @param userContribute 投稿
     * @return 结果
     */
    @Override
    public int insertUserContribute(UserContribute userContribute) {
        userContribute.setCreateTime(DateUtils.getNowDate());
        userContribute.setId(IdUtil.getSnowflakeNextId());
        return userContributeMapper.insertUserContribute(userContribute);
    }

    /**
     * 修改投稿
     *
     * @param userContribute 投稿
     * @return 结果
     */
    @Override
    public int updateUserContribute(UserContribute userContribute) {
        userContribute.setUpdateTime(DateUtils.getNowDate());
        if (ObjectUtil.isNotEmpty(userContribute.getImgUrlList())){
            String string = JSONArray.toJSON(userContribute.getImgUrlList()).toString();
            userContribute.setImgUrls(string);
        }


        if (ObjectUtil.isNotEmpty(userContribute.getAlbumIds())){
            QueryWrapper<UserContributeAlbum> userContributeAlbumQueryWrapper = new QueryWrapper<>();
            userContributeAlbumQueryWrapper.lambda().eq(UserContributeAlbum::getContributeId,userContribute.getId());
            userContributeAlbumService.remove(userContributeAlbumQueryWrapper);
            userContribute.getAlbumIds().forEach(t -> {
                userContributeAlbumService.insertUserContributeAlbum(userContribute.getId(),t);
            });
        }
        return userContributeMapper.updateUserContribute(userContribute);
    }

    /**
     * 批量删除投稿
     *
     * @param ids 需要删除的投稿主键
     * @return 结果
     */
    @Override
    public int deleteUserContributeByIds(Long[] ids) {
        return userContributeMapper.deleteUserContributeByIds(ids);
    }

    /**
     * 删除投稿信息
     *
     * @param id 投稿主键
     * @return 结果
     */
    @Override
    public int deleteUserContributeById(Long id) {
        return userContributeMapper.deleteUserContributeById(id);
    }


    public UserContributeResponse getOneUserContributeResponse(UserContribute userContribute){
        AppUser appUser = appUserService.getById(userContribute.getUserId());
        if (ObjectUtil.isNotEmpty(appUser)){
            UserContributeResponse userContributeResponse = BeanUtil.toBean(userContribute, UserContributeResponse.class);
            userContributeResponse.setUserName(appUser.getUserName());
            userContributeResponse.setUserAccount(appUser.getUserAccount());
            userContributeResponse.setCheckStateName(CheckState.getEnumType(userContribute.getCheckState()).getInfo());
            if (ObjectUtil.isEmpty(userContributeResponse.getCheckTime())){
                userContributeResponse.setCheckTime(null);
            }
            QueryWrapper<UserContributeAlbum> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UserContributeAlbum::getContributeId,userContribute.getId());
            List<Long> albumIds = userContributeAlbumService.list(wrapper).stream().map(x -> x.getAlbumId()).collect(Collectors.toList());

            if (albumIds.size() != 0){
                QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
                albumQueryWrapper.lambda().in(Album::getId,albumIds);
                List<Album> albums = albumService.list(albumQueryWrapper);
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < albums.size(); i++) {
                    if (i == albums.size() - 1) {
                        stringBuffer.append(albums.get(i).getAlbumName());
                    } else {
                        stringBuffer.append(albums.get(i).getAlbumName() + ",");
                    }
                }
                userContributeResponse.setAlbumNames(stringBuffer.toString());
            }else {
                userContributeResponse.setAlbumNames("雨果漫谈");
            }

            return userContributeResponse;
        }else {
            return null;
        }
    }
}
