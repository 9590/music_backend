package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.AlbumUser;
import org.springframework.stereotype.Repository;

/**
 * 专辑参与艺人Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Repository
public interface AlbumUserMapper extends BaseMapper<AlbumUser>
{
    /**
     * 查询专辑参与艺人
     * 
     * @param id 专辑参与艺人主键
     * @return 专辑参与艺人
     */
    public AlbumUser selectAlbumUserById(Long id);

    /**
     * 查询专辑参与艺人列表
     * 
     * @param albumUser 专辑参与艺人
     * @return 专辑参与艺人集合
     */
    public List<AlbumUser> selectAlbumUserList(AlbumUser albumUser);

    /**
     * 新增专辑参与艺人
     * 
     * @param albumUser 专辑参与艺人
     * @return 结果
     */
    public int insertAlbumUser(AlbumUser albumUser);

    /**
     * 修改专辑参与艺人
     * 
     * @param albumUser 专辑参与艺人
     * @return 结果
     */
    public int updateAlbumUser(AlbumUser albumUser);

    /**
     * 删除专辑参与艺人
     * 
     * @param id 专辑参与艺人主键
     * @return 结果
     */
    public int deleteAlbumUserById(Long id);

    /**
     * 批量删除专辑参与艺人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlbumUserByIds(Long[] ids);
}
