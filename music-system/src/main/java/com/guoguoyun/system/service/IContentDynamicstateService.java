package com.guoguoyun.system.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.ContentDynamicstate;
import com.guoguoyun.system.response.ContentDynamicstateResponse;

/**
 * 内容管理-动态管理Service接口
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
public interface IContentDynamicstateService extends IService<ContentDynamicstate>
{
    /**
     * 查询内容管理-动态管理
     * 
     * @param id 内容管理-动态管理主键
     * @return 内容管理-动态管理
     */
    public ContentDynamicstateResponse selectContentDynamicstateById(Long id);

    /**
     * 查询内容管理-动态管理列表
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 内容管理-动态管理集合
     */
    public PageInfo selectContentDynamicstateList(ContentDynamicstate contentDynamicstate);

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
     * 批量删除内容管理-动态管理
     * 
     * @param ids 需要删除的内容管理-动态管理主键集合
     * @return 结果
     */
    public int deleteContentDynamicstateByIds(Long[] ids);

    /**
     * 删除内容管理-动态管理信息
     * 
     * @param id 内容管理-动态管理主键
     * @return 结果
     */
    public int deleteContentDynamicstateById(Long id);

    public List<AppUser> addUserList(String remark);
}
