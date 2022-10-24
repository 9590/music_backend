package com.guoguoyun.system.service;

import java.util.List;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.Classify;

/**
 * 商城管理-分类管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
public interface IClassifyService extends IService<Classify>
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
     * 批量删除商城管理-分类管理
     * 
     * @param ids 需要删除的商城管理-分类管理主键集合
     * @return 结果
     */
    public int deleteClassifyByIds(Long[] ids);

    /**
     * 删除商城管理-分类管理信息
     * 
     * @param id 商城管理-分类管理主键
     * @return 结果
     */
    public int deleteClassifyById(Long id);


    public PageInfo treeList(Boolean type,Classify classify);
    public List<Tree<String>> treeList(Integer type);
}
