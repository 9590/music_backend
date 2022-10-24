package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.UserContributeAlbum;

/**
 * 投稿-专辑Service接口
 * 
 * @author ruoyi
 * @date 2022-02-25
 */
public interface IUserContributeAlbumService extends IService<UserContributeAlbum>
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
    public void insertUserContributeAlbum(Long contributeId,Long albumId);

    /**
     * 修改投稿-专辑
     * 
     * @param userContributeAlbum 投稿-专辑
     * @return 结果
     */
    public int updateUserContributeAlbum(UserContributeAlbum userContributeAlbum);

    /**
     * 批量删除投稿-专辑
     * 
     * @param ids 需要删除的投稿-专辑主键集合
     * @return 结果
     */
    public int deleteUserContributeAlbumByIds(Long[] ids);

    /**
     * 删除投稿-专辑信息
     * 
     * @param id 投稿-专辑主键
     * @return 结果
     */
    public int deleteUserContributeAlbumById(Long id);
}
