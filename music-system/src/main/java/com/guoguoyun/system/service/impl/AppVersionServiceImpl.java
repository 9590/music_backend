package com.guoguoyun.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.AppVersionMapper;
import com.guoguoyun.system.domain.AppVersion;
import com.guoguoyun.system.service.IAppVersionService;

/**
 * app版本Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements IAppVersionService
{
    @Autowired
    private AppVersionMapper appVersionMapper;

    /**
     * 查询app版本
     *
     * @return app版本
     */
    @Override
    public AppVersion selectAppVersionById()
    {
        QueryWrapper<AppVersion> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(AppVersion::getCreateTime);
        List<AppVersion> list = this.list(wrapper);
        return list.get(0);
    }

    /**
     * 查询app版本列表
     * 
     * @param appVersion app版本
     * @return app版本
     */
    @Override
    public List<AppVersion> selectAppVersionList(AppVersion appVersion)
    {
        return appVersionMapper.selectAppVersionList(appVersion);
    }

    /**
     * 新增app版本
     * 
     * @param appVersion app版本
     * @return 结果
     */
    @Override
    public int insertAppVersion(AppVersion appVersion)
    {
        appVersion.setCreateTime(DateUtils.getNowDate());
        appVersion.setId(IdUtil.getSnowflakeNextId());
        return appVersionMapper.insertAppVersion(appVersion);
    }

    /**
     * 修改app版本
     * 
     * @param appVersion app版本
     * @return 结果
     */
    @Override
    public int updateAppVersion(AppVersion appVersion)
    {
        appVersion.setUpdateTime(DateUtils.getNowDate());
        return appVersionMapper.updateAppVersion(appVersion);
    }

    /**
     * 批量删除app版本
     * 
     * @param ids 需要删除的app版本主键
     * @return 结果
     */
    @Override
    public int deleteAppVersionByIds(Long[] ids)
    {
        return appVersionMapper.deleteAppVersionByIds(ids);
    }

    /**
     * 删除app版本信息
     * 
     * @param id app版本主键
     * @return 结果
     */
    @Override
    public int deleteAppVersionById(Long id)
    {
        return appVersionMapper.deleteAppVersionById(id);
    }
}
