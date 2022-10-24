package com.guoguoyun.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.PlayRecordMapper;
import com.guoguoyun.system.domain.PlayRecord;
import com.guoguoyun.system.service.IPlayRecordService;

/**
 * 播放记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
@Service
public class PlayRecordServiceImpl extends ServiceImpl<PlayRecordMapper, PlayRecord> implements IPlayRecordService
{
    @Autowired
    private PlayRecordMapper playRecordMapper;

    /**
     * 查询播放记录
     * 
     * @param id 播放记录主键
     * @return 播放记录
     */
    @Override
    public PlayRecord selectPlayRecordById(Long id)
    {
        return playRecordMapper.selectPlayRecordById(id);
    }

    /**
     * 查询播放记录列表
     * 
     * @param playRecord 播放记录
     * @return 播放记录
     */
    @Override
    public List<PlayRecord> selectPlayRecordList(PlayRecord playRecord)
    {
        return playRecordMapper.selectPlayRecordList(playRecord);
    }

    /**
     * 新增播放记录
     * 
     * @param playRecord 播放记录
     * @return 结果
     */
    @Override
    public int insertPlayRecord(PlayRecord playRecord)
    {
        return playRecordMapper.insertPlayRecord(playRecord);
    }

    /**
     * 修改播放记录
     * 
     * @param playRecord 播放记录
     * @return 结果
     */
    @Override
    public int updatePlayRecord(PlayRecord playRecord)
    {
        return playRecordMapper.updatePlayRecord(playRecord);
    }

    /**
     * 批量删除播放记录
     * 
     * @param ids 需要删除的播放记录主键
     * @return 结果
     */
    @Override
    public int deletePlayRecordByIds(Long[] ids)
    {
        return playRecordMapper.deletePlayRecordByIds(ids);
    }

    /**
     * 删除播放记录信息
     * 
     * @param id 播放记录主键
     * @return 结果
     */
    @Override
    public int deletePlayRecordById(Long id)
    {
        return playRecordMapper.deletePlayRecordById(id);
    }
}
