package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.AppUser;
import org.springframework.stereotype.Repository;

/**
 * app端用户Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Repository
public interface AppUserMapper extends BaseMapper<AppUser>
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
    public List<AppUser> selectAppUserListLikeByNameOrAccount(String remark);

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
     * 删除app端用户
     * 
     * @param id app端用户主键
     * @return 结果
     */
    public int deleteAppUserById(Long id);

    /**
     * 批量删除app端用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppUserByIds(Long[] ids);
}
