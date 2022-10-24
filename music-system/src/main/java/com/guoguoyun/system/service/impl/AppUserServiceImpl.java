package com.guoguoyun.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.constant.HttpStatus;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.exception.ServiceException;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.common.utils.SecurityUtils;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.domain.AlbumUser;
import com.guoguoyun.system.params.AppUserActorParams;
import com.guoguoyun.system.response.AppUserActorResponse;
import com.guoguoyun.system.response.AppUserResponse;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.system.service.IAlbumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AppUserMapper;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.service.IAppUserService;

/**
 * app端用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService
{
    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private IAlbumUserService albumUserService;

    @Autowired
    private IAlbumService albumService;

    /**
     * 查询app端用户
     * 
     * @param id app端用户主键
     * @return app端用户
     */
    @Override
    public AppUser selectAppUserById(Long id)
    {
        return appUserMapper.selectAppUserById(id);
    }

    /**
     * 查询app端用户列表
     * 
     * @param appUser app端用户
     * @return app端用户
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        List<AppUser> appUsers = appUserMapper.selectAppUserList(appUser);
        return appUsers;
    }

    /**
     * 新增app端用户
     * 
     * @param appUser app端用户
     * @return 结果
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        appUser.setCreateTime(DateUtils.getNowDate());
        appUser.setId(IdUtil.getSnowflakeNextId());
        appUser.setUpdateTime(DateUtils.getNowDate());
        appUser.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        appUser.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        return appUserMapper.insertAppUser(appUser);
    }

    /**
     * 修改app端用户
     * 
     * @param appUser app端用户
     * @return 结果
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        appUser.setUpdateTime(DateUtils.getNowDate());
        return appUserMapper.updateAppUser(appUser);
    }

    /**
     * 批量删除app端用户
     * 
     * @param ids 需要删除的app端用户主键
     * @return 结果
     */
    @Override
    public int deleteAppUserByIds(Long[] ids)
    {
        AppUser appUser = this.selectAppUserById(ids[0]);
        if (appUser.getUserType() == MusicCode.USER_TYPE_ARTIST){
            QueryWrapper<AlbumUser> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(AlbumUser::getUserId,appUser.getId());
            albumUserService.remove(wrapper);
        }
        return appUserMapper.deleteAppUserByIds(ids);
    }

    /**
     * 删除app端用户信息
     * 
     * @param id app端用户主键
     * @return 结果
     */
    @Override
    public int deleteAppUserById(Long id)
    {
        return appUserMapper.deleteAppUserById(id);
    }

    @Override
    public void saveActor(AppUserActorParams appUserActorParams) {

        QueryWrapper<AppUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AppUser::getUserAccount,appUserActorParams.getUserAccount());
        AppUser user = this.getOne(wrapper);
        if (ObjectUtil.isNotEmpty(user)){
            throw new ServiceException("账号重复", HttpStatus.ERROR);
        }

        AppUser appUser = BeanUtil.toBean(appUserActorParams, AppUser.class);
        appUser.setUserType(MusicCode.USER_TYPE_ARTIST);
        this.insertAppUser(appUser);

        if (ObjectUtil.isNotEmpty(appUserActorParams.getAlbumIds())){
            appUserActorParams.getAlbumIds().forEach(t -> {
                AlbumUser albumUser = new AlbumUser();
                albumUser.setAlbumId(t);
                albumUser.setUserId(appUser.getId());
                albumUserService.insertAlbumUser(albumUser);
            });
        }


    }

    @Override
    public void updateActor(AppUserActorParams appUserActorParams) {
        AppUser appUser = BeanUtil.toBean(appUserActorParams, AppUser.class);
        appUser.setUpdateBy(SecurityUtils.getLoginUser().getUsername());
        this.updateAppUser(appUser);

        if (ObjectUtil.isNotEmpty(appUserActorParams.getAlbumIds())){
            QueryWrapper<AlbumUser> albumUserQueryWrapper = new QueryWrapper<>();
            albumUserQueryWrapper.lambda().eq(AlbumUser::getUserId,appUser.getId());
            albumUserService.remove(albumUserQueryWrapper);
            appUserActorParams.getAlbumIds().forEach(t -> {
                AlbumUser albumUser = new AlbumUser();
                albumUser.setAlbumId(t);
                albumUser.setUserId(appUser.getId());
                albumUserService.insertAlbumUser(albumUser);
            });
        }
    }

    @Override
    public AppUserActorResponse getOneActorUser(Long id) {
        AppUser appUser = appUserMapper.selectAppUserById(id);
        AppUserActorResponse appUserActorResponse = BeanUtil.toBean(appUser, AppUserActorResponse.class);

        QueryWrapper<AlbumUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AlbumUser::getUserId,id);
        List<Long> albumIds = albumUserService.list(wrapper).stream().map(t -> t.getAlbumId()).collect(Collectors.toList());

        if (albumIds.size() != 0){
            QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
            albumQueryWrapper.lambda().in(Album::getId,albumIds);
            List<Album> albums = albumService.list(albumQueryWrapper);
            appUserActorResponse.setAlbumIds(albums);
        }

        return appUserActorResponse;
    }
}
