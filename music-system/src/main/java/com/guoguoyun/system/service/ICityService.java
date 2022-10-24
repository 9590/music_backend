package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guoguoyun.system.domain.City;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2022-02-23
 */
public interface ICityService extends IService<City>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public City selectCityById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param city 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<City> selectCityList(City city);

    /**
     * 新增【请填写功能名称】
     * 
     * @param city 【请填写功能名称】
     * @return 结果
     */
    public int insertCity(City city);

    /**
     * 修改【请填写功能名称】
     * 
     * @param city 【请填写功能名称】
     * @return 结果
     */
    public int updateCity(City city);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteCityByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCityById(String id);
}
