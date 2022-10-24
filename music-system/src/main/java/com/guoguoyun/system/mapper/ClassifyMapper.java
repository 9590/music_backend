package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.Classify;
import org.springframework.stereotype.Repository;

/**
 * 商城管理-分类管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Repository
public interface ClassifyMapper extends BaseMapper<Classify>
{
    /**
     * 查询商城管理-分类管理
     * 
     * @param id 商城管理-分类管理主键
     * @return 商城管理-分类管理
     */
    public Classify selectClassifyById(Long id);

    /**
     * 查询商城管理-分类管理列表
     * 
     * @param classify 商城管理-分类管理
     * @return 商城管理-分类管理集合
     */
    public List<Classify> selectClassifyList(Classify classify);

    /**
     * 新增商城管理-分类管理
     * 
     * @param classify 商城管理-分类管理
     * @return 结果
     */
    public int insertClassify(Classify classify);

    /**
     * 修改商城管理-分类管理
     * 
     * @param classify 商城管理-分类管理
     * @return 结果
     */
    public int updateClassify(Classify classify);

    /**
     * 删除商城管理-分类管理
     * 
     * @param id 商城管理-分类管理主键
     * @return 结果
     */
    public int deleteClassifyById(Long id);

    /**
     * 批量删除商城管理-分类管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassifyByIds(Long[] ids);
}
