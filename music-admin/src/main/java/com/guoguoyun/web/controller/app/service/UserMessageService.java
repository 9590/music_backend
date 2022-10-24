package com.guoguoyun.web.controller.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.ContactUs;
import com.guoguoyun.system.domain.OperationOrder;
import com.guoguoyun.system.domain.PlayRecord;
import com.guoguoyun.system.domain.vo.AppInfoVo;
import com.guoguoyun.system.params.app.UserPasswordParams;

import java.util.List;

public interface UserMessageService {

    /**
     * 修改密码
     * @param userPasswordParams
     */
    public void updatePassword(UserPasswordParams userPasswordParams);


    /**
     * 获取隐私政策或联系我们
     * @param type
     * @return
     */
    public ContactUs getRoleInfo(Integer type);


    /**
     * 我的页面数据
     * @param userId
     */
    public void myInfo(Long userId);


    /**
     * 个人信息
     * @param userId
     */
    public AppUser userMessageInfo(Long userId);

    /**
     * 修改个人信息
     * @param appUser
     */
    public void editUserMessage(AppUser appUser);

    /**
     * 通知列表
     */
    public List<AppInfoVo> informList(Long userId);

    /**
     * 最近播放
     */
    public List<PlayRecord>  userPlayLog(Long userId, Long type);

    /**
     * 用户订单记录
     * @param userId
     * @param type
     * @return
     */
    public Page<OperationOrder> userOrderList(Long userId, Long type, Integer pageNum, Integer pageSize);
}
