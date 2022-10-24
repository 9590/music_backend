package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.PlayRecord;
import org.springframework.stereotype.Repository;

/**
 * 播放记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
@Repository
public interface PlayRecordMapper extends BaseMapper<PlayRecord>
{
    /**
     * 查询播放记录
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
     * 删除播放记录
     * 
     * @param id 播放记录主键
     * @return 结果
     */
    public int deletePlayRecordById(Long id);

    /**
     * 批量删除播放记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlayRecordByIds(Long[] ids);
}
