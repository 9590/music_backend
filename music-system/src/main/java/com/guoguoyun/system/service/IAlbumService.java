package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.system.domain.Album;
import com.guoguoyun.system.params.AlbumExeclParams;
import com.guoguoyun.system.params.AlbumParams;
import com.guoguoyun.system.response.AlbumByIdResponse;

/**
 * 专辑管理-专辑库Service接口
 *
 * @author ruoyi
 * @date 2022-02-11
 */
public interface IAlbumService extends IService<Album>
{
    /**
     * 查询专辑管理-专辑库
     *
     * @param id 专辑管理-专辑库主键
     * @return 专辑管理-专辑库
     */
    public AlbumByIdResponse selectAlbumById(Long id);

    /**
     * 查询专辑管理-专辑库列表
     *
     * @param album 专辑管理-专辑库
     * @return 专辑管理-专辑库集合
     */
    public PageInfo selectAlbumList(Album album);

    /**
     * 新增专辑管理-专辑库
     *
     * @param album 专辑管理-专辑库
     * @return 结果
     */
    public int insertAlbum(Album album);
    public void insertAlbum(AlbumParams albumParams);

    /**
     * 修改专辑管理-专辑库
     *
     * @param albumParams 专辑管理-专辑库
     * @return 结果
     */
    public int updateAlbum(AlbumParams albumParams);

    /**
     * 批量删除专辑管理-专辑库
     *
     * @param ids 需要删除的专辑管理-专辑库主键集合
     * @return 结果
     */
    public int deleteAlbumByIds(Long[] ids);

    /**
     * 删除专辑管理-专辑库信息
     *
     * @param id 专辑管理-专辑库主键
     * @return 结果
     */
    public int deleteAlbumById(Long id);

    /**
     * 导入
     * @param albumList
     */
    public void importData(List<AlbumExeclParams> albumList);

    /**
     * 自动生成专辑编号
     * @return
     */
    AjaxResult getMaxAlbumCode();
}
