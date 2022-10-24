package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.AppVersion;
import org.springframework.stereotype.Repository;

/**
 * app版本Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
@Repository
public interface AppVersionMapper extends BaseMapper<AppVersion>
{
    /**
     * 查询app版本
     * 
     * @param id app版本主键
     * @return app版本
     */
    public AppVersion selectAppVersionById(Long id);

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
     * 删除app版本
     * 
     * @param id app版本主键
     * @return 结果
     */
    public int deleteAppVersionById(Long id);

    /**
     * 批量删除app版本
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppVersionByIds(Long[] ids);
}
