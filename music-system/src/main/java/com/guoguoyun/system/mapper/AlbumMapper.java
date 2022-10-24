package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.Album;
import org.springframework.stereotype.Repository;

/**
 * 专辑管理-专辑库Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-11
 */
@Repository
public interface AlbumMapper extends BaseMapper<Album>
{
    /**
     * 查询专辑管理-专辑库
     * 
     * @param id 专辑管理-专辑库主键
     * @return 专辑管理-专辑库
     */
    public Album selectAlbumById(Long id);

    /**
     * 查询专辑管理-专辑库列表
     * 
     * @param album 专辑管理-专辑库
     * @return 专辑管理-专辑库集合
     */
    public List<Album> selectAlbumList(Album album);
    public List<Album> selectAlbumListLike(Album album);
    public List<Album> selectAlbumListByArticleId(Long articleId);

    /**
     * 新增专辑管理-专辑库
     * 
     * @param album 专辑管理-专辑库
     * @return 结果
     */
    public int insertAlbum(Album album);

    /**
     * 修改专辑管理-专辑库
     * 
     * @param album 专辑管理-专辑库
     * @return 结果
     */
    public int updateAlbum(Album album);

    /**
     * 删除专辑管理-专辑库
     * 
     * @param id 专辑管理-专辑库主键
     * @return 结果
     */
    public int deleteAlbumById(Long id);

    /**
     * 批量删除专辑管理-专辑库
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAlbumByIds(Long[] ids);
}
