package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.UserContribute;
import com.guoguoyun.system.response.UserContributeResponse;

/**
 * 投稿Service接口
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
public interface IUserContributeService extends IService<UserContribute>
{
    /**
     * 查询投稿
     * 
     * @param id 投稿主键
     * @return 投稿
     */
    public UserContributeResponse selectUserContributeById(Long id);

    /**
     * 查询投稿列表
     * 
     * @param userContribute 投稿
     * @return 投稿集合
     */
    public PageInfo selectUserContributeList(UserContribute userContribute);

    /**
     * 新增投稿
     * 
     * @param userContribute 投稿
     * @return 结果
     */
    public int insertUserContribute(UserContribute userContribute);

    /**
     * 修改投稿
     * 
     * @param userContribute 投稿
     * @return 结果
     */
    public int updateUserContribute(UserContribute userContribute);

    /**
     * 批量删除投稿
     * 
     * @param ids 需要删除的投稿主键集合
     * @return 结果
     */
    public int deleteUserContributeByIds(Long[] ids);

    /**
     * 删除投稿信息
     * 
     * @param id 投稿主键
     * @return 结果
     */
    public int deleteUserContributeById(Long id);
}
