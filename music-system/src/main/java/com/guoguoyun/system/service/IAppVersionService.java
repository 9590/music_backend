package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.AppVersion;

/**
 * app版本Service接口
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
public interface IAppVersionService extends IService<AppVersion>
{
    /**
     * 查询app版本
     *
     * @return app版本
     */
    public AppVersion selectAppVersionById();

    /**
     * 查询app版本列表
     * 
     * @param appVersion app版本
     * @return app版本集合
     */
    public List<AppVersion> selectAppVersionList(AppVersion appVersion);

    /**
     * 新增app版本
     * 
     * @param appVersion app版本
     * @return 结果
     */
    public int insertAppVersion(AppVersion appVersion);

    /**
     * 修改app版本
     * 
     * @param appVersion app版本
     * @return 结果
     */
    public int updateAppVersion(AppVersion appVersion);

    /**
     * 批量删除app版本
     * 
     * @param ids 需要删除的app版本主键集合
     * @return 结果
     */
    public int deleteAppVersionByIds(Long[] ids);

    /**
     * 删除app版本信息
     * 
     * @param id app版本主键
     * @return 结果
     */
    public int deleteAppVersionById(Long id);
}
