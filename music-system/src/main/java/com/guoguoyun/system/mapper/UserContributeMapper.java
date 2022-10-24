package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.UserContribute;
import org.springframework.stereotype.Repository;

/**
 * 投稿Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@Repository
public interface UserContributeMapper extends BaseMapper<UserContribute>
{
    /**
     * 查询投稿
     * 
     * @param id 投稿主键
     * @return 投稿
     */
    public UserContribute selectUserContributeById(Long id);

    /**
     * 查询投稿列表
     * 
     * @param userContribute 投稿
     * @return 投稿集合
     */
    public List<UserContribute> selectUserContributeList(UserContribute userContribute);
    public List<UserContribute> selectUserContributeListLike(UserContribute userContribute);

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
     * 删除投稿
     * 
     * @param id 投稿主键
     * @return 结果
     */
    public int deleteUserContributeById(Long id);

    /**
     * 批量删除投稿
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserContributeByIds(Long[] ids);
}
