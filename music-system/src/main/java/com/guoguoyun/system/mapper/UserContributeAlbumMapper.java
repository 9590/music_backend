package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.UserContributeAlbum;
import org.springframework.stereotype.Repository;

/**
 * 投稿-专辑Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
@Repository
public interface UserContributeAlbumMapper extends BaseMapper<UserContributeAlbum>
{
    /**
     * 查询投稿-专辑
     * 
     * @param id 投稿-专辑主键
     * @return 投稿-专辑
     */
    public UserContributeAlbum selectUserContributeAlbumById(Long id);

    /**
     * 查询投稿-专辑列表
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 投稿-专辑集合
     */
    public List<UserContributeAlbum> selectUserContributeAlbumList(UserContributeAlbum userContributeAlbum);

    /**
     * 新增投稿-专辑
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 结果
     */
    public int insertUserContributeAlbum(UserContributeAlbum userContributeAlbum);

    /**
     * 修改投稿-专辑
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 结果
     */
    public int updateUserContributeAlbum(UserContributeAlbum userContributeAlbum);

    /**
     * 删除投稿-专辑
     * 
     * @param id 投稿-专辑主键
     * @return 结果
     */
    public int deleteUserContributeAlbumById(Long id);

    /**
     * 批量删除投稿-专辑
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserContributeAlbumByIds(Long[] ids);
}
