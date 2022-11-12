package com.guoguoyun.web.controller.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.domain.vo.AppInfoVo;
import com.guoguoyun.system.params.app.UserPasswordParams;
import com.guoguoyun.system.service.*;
import com.guoguoyun.web.controller.app.service.IndexService;
import com.guoguoyun.web.controller.app.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IContactUsService contactUsService;

    @Autowired
    private IUserLikeFollowInformService userLikeFollowInformService;

    @Autowired
    private IPlayRecordService playRecordService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private IOperationOrderService orderService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IAlbumSongService albumSongService;


    /**
     * 修改密码
     * @param userPasswordParams
     */
    @Override
    public void updatePassword(UserPasswordParams userPasswordParams) {
        AppUser appUser = appUserService.getById(userPasswordParams.getUserId());
        if (ObjectUtil.isEmpty(appUser)){
            throw new SecurityException("无该账号");
        }

        if (ObjectUtil.isEmpty(appUser.getPassword())){
            throw new SecurityException("该账号无密码");
        }

        if (!userPasswordParams.getOldPassword().equals(appUser.getPassword())){
            throw new SecurityException("旧密码输入不正确");
        }

        if (!userPasswordParams.getNewPasswordOne().equals(userPasswordParams.getNewPasswordTwo())){
            throw new SecurityException("两次新密码输入不正确");
        }

        appUser.setPassword(userPasswordParams.getNewPasswordOne());
        appUserService.updateById(appUser);
    }


    /**
     * 隐私政策或联系我们
     * @param type
     * @return
     */
    @Override
    public ContactUs getRoleInfo(Integer type) {
        QueryWrapper<ContactUs> contactUsQueryWrapper = new QueryWrapper<>();
        contactUsQueryWrapper.lambda().eq(ContactUs::getType,type);
        return contactUsService.getOne(contactUsQueryWrapper);
    }

    @Override
    public void myInfo(Long userId) {

    }

    /**
     * 个人信息
     * @param userId
     * @return
     */
    @Override
    public AppUser userMessageInfo(Long userId) {
        AppUser appUser = appUserService.getById(userId);
        return appUser;
    }

    @Override
    public void editUserMessage(AppUser appUser) {
        appUserService.updateAppUser(appUser);
    }


    /**
     * 通知列表
     * @param userId
     */
    @Override
    public List<AppInfoVo> informList(Long userId) {
        QueryWrapper<UserLikeFollowInform> userLikeFollowInformQueryWrapper = new QueryWrapper<>();
        userLikeFollowInformQueryWrapper.lambda().eq(UserLikeFollowInform::getFromId,userId);
        userLikeFollowInformQueryWrapper.lambda().orderByDesc(UserLikeFollowInform::getCreateTime);
        List<UserLikeFollowInform> followInformList = userLikeFollowInformService.list(userLikeFollowInformQueryWrapper);
        List<AppInfoVo> infoVoList = followInformList.stream().map(t -> {
            AppInfoVo appInfoVo = new AppInfoVo();
            appInfoVo.setCreateTime(t.getCreateTime());
            AppUser user = appUserService.getById(t.getUserId());
            appInfoVo.setNickName(user.getUserName());
            if (ObjectUtil.isNotEmpty(appInfoVo.getUserAvatar())) {
                appInfoVo.setUserAvatar(user.getUserAvatar());
            }
            if (t.getType() == 2) {
                appInfoVo.setContent("关注了你");
            } else {
                if (t.getState() == 1) {
                    appInfoVo.setContent("点赞了你发表的投稿！");
                } else {
                    appInfoVo.setContent("点赞了你发表的动态！");
                }
            }

            return appInfoVo;
        }).collect(Collectors.toList());

        QueryWrapper<ContactUs> contactUsQueryWrapper = new QueryWrapper<>();
        contactUsQueryWrapper.lambda().orderByDesc(ContactUs::getCreateTime);
        contactUsQueryWrapper.lambda().eq(ContactUs::getType,3);
        List<ContactUs> contactUsList = contactUsService.list(contactUsQueryWrapper);
        List<AppInfoVo> voList = contactUsList.stream().map(t -> {
            AppInfoVo appInfoVo = new AppInfoVo();
            appInfoVo.setCreateTime(t.getCreateTime());
            appInfoVo.setNickName("客服小助手");
            appInfoVo.setUserAvatar("http://localhost:8094/profile/upload/2022/06/01/a9f36a05-2267-4b25-b12a-e5df3cfb9f03.jpg");
            appInfoVo.setContent(t.getRemark());

            return appInfoVo;
        }).collect(Collectors.toList());

        infoVoList.addAll(voList);
        infoVoList = infoVoList.stream().sorted(Comparator.comparing(AppInfoVo::getCreateTime,Comparator.reverseOrder())).collect(Collectors.toList());
        return infoVoList;
    }

    /**
     * 最近播放
     * @param userId
     * @param type 1为最近一周  2为所有时间
     */
    @Override
    public List<PlayRecord>  userPlayLog(Long userId, Long type) {

        List<PlayRecord> list = new ArrayList<>();
        System.out.println(type);
        if (type == 1){
            Map<String, Object> map = new HashMap<>();
            map.put("type",1);
            PlayRecord record = new PlayRecord();
            record.setUserId(userId);
            record.setParams(map);
            list = playRecordService.selectPlayRecordList(record);
        }else {
            QueryWrapper<PlayRecord> playRecordQueryWrapper = new QueryWrapper<>();
            playRecordQueryWrapper.lambda().eq(PlayRecord::getUserId,userId);
            playRecordQueryWrapper.lambda().orderByDesc(PlayRecord::getPlayTime);
            list = playRecordService.list(playRecordQueryWrapper);
        }

        list.forEach(t -> {
            AlbumSong albumSong = albumSongService.getById(t.getAlbumId());
            t.setAlbumSong(albumSong);
        });

        return list;
    }

    /**
     * 用户订单记录
     * @param userId
     * @param type 1为专辑  2为单曲
     * @return
     */
    @Override
    public Page<OperationOrder> userOrderList(Long userId, Long type,Integer pageNum,Integer pageSize) {
        QueryWrapper<OperationOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.lambda().eq(OperationOrder::getUserId,userId);
        orderQueryWrapper.lambda().eq(OperationOrder::getType,type);
        orderQueryWrapper.lambda().orderByDesc(OperationOrder::getCreateTime);
        Page<OperationOrder> page = new Page<>(pageNum, pageSize);
        Page<OperationOrder> page1 = orderService.page(page, orderQueryWrapper);

        List<OperationOrder> orderList = page1.getRecords();

        orderList.forEach(t ->{
            if (t.getType() == 1){
                Album byId = albumService.getById(t.getShopId());

                t.setAlbum(byId);
            }else {
                AlbumSong byId = albumSongService.getById(t.getShopId());
                t.setAlbumSong(byId);
            }
        });
        page1.setRecords(orderList);
        return page1;
    }
}
