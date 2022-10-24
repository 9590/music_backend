package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.PlayRecord;

/**
 * 播放记录Service接口
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
public interface IPlayRecordService extends IService<PlayRecord>
{
    /**
     *
     * @param id 播放记录主键
     * @return 播放记录
     */
    public PlayRecord selectPlayRecordById(Long id);

    /**
     * 查询播放记录列表
     * 
     * @param playRecord 播放记录
     * @return 播放记录集合
     */
    public List<PlayRecord> selectPlayRecordList(PlayRecord playRecord);

    /**
     * 新增播放记录
     * 
     * @param playRecord 播放记录
     * @return 结果
     */
    public int insertPlayRecord(PlayRecord playRecord);

    /**
     * 修改播放记录
     * 
     * @param playRecord 播放记录
     * @return 结果
     */
    public int updatePlayRecord(PlayRecord playRecord);

    /**
     * 批量删除播放记录
     * 
     * @param ids 需要删除的播放记录主键集合
     * @return 结果
     */
    public int deletePlayRecordByIds(Long[] ids);

    /**
     * 删除播放记录信息
     * 
     * @param id 播放记录主键
     * @return 结果
     */
    public int deletePlayRecordById(Long id);
}
