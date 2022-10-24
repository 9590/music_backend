package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.params.AppUserActorParams;
import com.guoguoyun.system.response.AppUserActorResponse;
import com.guoguoyun.system.response.AppUserResponse;

/**
 * app端用户Service接口
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
public interface IAppUserService extends IService<AppUser>
{
    /**
     * 查询app端用户
     * 
     * @param id app端用户主键
     * @return app端用户
     */
    public AppUser selectAppUserById(Long id);

    /**
     * 查询app端用户列表
     * 
     * @param appUser app端用户
     * @return app端用户集合
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    /**
     * 新增app端用户
     * 
     * @param appUser app端用户
     * @return 结果
     */
    public int insertAppUser(AppUser appUser);

    /**
     * 修改app端用户
     * 
     * @param appUser app端用户
     * @return 结果
     */
    public int updateAppUser(AppUser appUser);

    /**
     * 批量删除app端用户
     * 
     * @param ids 需要删除的app端用户主键集合
     * @return 结果
     */
    public int deleteAppUserByIds(Long[] ids);

    /**
     * 删除app端用户信息
     * 
     * @param id app端用户主键
     * @return 结果
     */
    public int deleteAppUserById(Long id);

    /**
     * 添加艺人
     * @param appUserActorParams
     */
    public void saveActor(AppUserActorParams appUserActorParams);

    /**
     * 修改艺人
     * @param appUserActorParams
     */
    public void updateActor(AppUserActorParams appUserActorParams);


    public AppUserActorResponse getOneActorUser(Long id);
}
