package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.ContentDynamicstate;
import org.springframework.stereotype.Repository;

/**
 * 内容管理-动态管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Repository
public interface ContentDynamicstateMapper extends BaseMapper<ContentDynamicstate>
{
    /**
     * 查询内容管理-动态管理
     * 
     * @param id 内容管理-动态管理主键
     * @return 内容管理-动态管理
     */
    public ContentDynamicstate selectContentDynamicstateById(Long id);

    /**
     * 查询内容管理-动态管理列表
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 内容管理-动态管理集合
     */
    public List<ContentDynamicstate> selectContentDynamicstateList(ContentDynamicstate contentDynamicstate);
    public List<ContentDynamicstate> selectContentDynamicstateListLike(ContentDynamicstate contentDynamicstate);

    /**
     * 新增内容管理-动态管理
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 结果
     */
    public int insertContentDynamicstate(ContentDynamicstate contentDynamicstate);

    /**
     * 修改内容管理-动态管理
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 结果
     */
    public int updateContentDynamicstate(ContentDynamicstate contentDynamicstate);

    /**
     * 删除内容管理-动态管理
     * 
     * @param id 内容管理-动态管理主键
     * @return 结果
     */
    public int deleteContentDynamicstateById(Long id);

    /**
     * 批量删除内容管理-动态管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContentDynamicstateByIds(Long[] ids);
}
