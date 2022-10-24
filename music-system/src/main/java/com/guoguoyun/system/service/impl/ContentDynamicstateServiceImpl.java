package com.guoguoyun.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.OperationAdvertising;
import com.guoguoyun.system.domain.vo.ImgUrlVo;
import com.guoguoyun.system.mapper.AppUserMapper;
import com.guoguoyun.system.response.ContentDynamicstateResponse;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.service.IAppUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.ContentDynamicstateMapper;
import com.guoguoyun.system.domain.ContentDynamicstate;
import com.guoguoyun.system.service.IContentDynamicstateService;

/**
 * 内容管理-动态管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-15
 */
@Service
public class ContentDynamicstateServiceImpl extends ServiceImpl<ContentDynamicstateMapper, ContentDynamicstate> implements IContentDynamicstateService
{
    @Autowired
    private ContentDynamicstateMapper contentDynamicstateMapper;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * 查询内容管理-动态管理
     * 
     * @param id 内容管理-动态管理主键
     * @return 内容管理-动态管理
     */
    @Override
    public ContentDynamicstateResponse selectContentDynamicstateById(Long id)
    {
        ContentDynamicstate contentDynamicstate = contentDynamicstateMapper.selectContentDynamicstateById(id);
        ContentDynamicstateResponse contentDynamicstateResponse = BeanUtil.toBean(contentDynamicstate, ContentDynamicstateResponse.class);
        AppUser appUser = appUserService.getById(contentDynamicstate.getUserId());
        if (ObjectUtil.isNotEmpty(appUser)){
            contentDynamicstateResponse.setUserName(appUser.getUserName());
            contentDynamicstateResponse.setUserAccount(appUser.getUserAccount());
        }
        if (ObjectUtil.isNotEmpty(contentDynamicstate.getImgUrls()) ){
            List<ImgUrlVo> imgUrls = JSONArray.parseArray(contentDynamicstate.getImgUrls(), ImgUrlVo.class);
            contentDynamicstateResponse.setImgUrlList(imgUrls);
        }
        if (ObjectUtil.isNotEmpty(contentDynamicstate.getVideoUrl()) ){
            List<ImgUrlVo> videoUrl = JSONArray.parseArray(contentDynamicstate.getVideoUrl(), ImgUrlVo.class);
            contentDynamicstateResponse.setVideoUrlList(videoUrl);
        }
        return contentDynamicstateResponse;
    }

    /**
     * 查询内容管理-动态管理列表
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 内容管理-动态管理
     */
    @Override
    public PageInfo selectContentDynamicstateList(ContentDynamicstate contentDynamicstate)
    {
        List<ContentDynamicstate> contentDynamicstates = contentDynamicstateMapper.selectContentDynamicstateListLike(contentDynamicstate);
        List<ContentDynamicstateResponse> contentDynamicstateResponses = contentDynamicstates.stream().map(t -> {
            ContentDynamicstateResponse contentDynamicstateResponse = BeanUtil.toBean(t, ContentDynamicstateResponse.class);
            if (t.getIssueType() == MusicCode.CONTENT_FRONT){
                contentDynamicstateResponse.setIssueTypeName("前端");
            }else {
                contentDynamicstateResponse.setIssueTypeName("后台");
            }
            AppUser appUser = appUserService.getById(t.getUserId());
            if (ObjectUtil.isNotEmpty(appUser)){
                contentDynamicstateResponse.setUserName(appUser.getUserName());
                contentDynamicstateResponse.setUserAccount(appUser.getUserAccount());
            }
            return contentDynamicstateResponse;
        }).collect(Collectors.toList());
        PageInfo<ContentDynamicstate> page = new PageInfo<>(contentDynamicstates);
        PageInfo<ContentDynamicstateResponse> pageInfo = new PageInfo<>(contentDynamicstateResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(contentDynamicstateResponses);
        return pageInfo;
    }

    /**
     * 新增内容管理-动态管理
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 结果
     */
    @Override
    public int insertContentDynamicstate(ContentDynamicstate contentDynamicstate)
    {
        contentDynamicstate.setCreateTime(DateUtils.getNowDate());
        contentDynamicstate.setId(IdUtil.getSnowflakeNextId());
        return contentDynamicstateMapper.insertContentDynamicstate(contentDynamicstate);
    }

    /**
     * 修改内容管理-动态管理
     * 
     * @param contentDynamicstate 内容管理-动态管理
     * @return 结果
     */
    @Override
    public int updateContentDynamicstate(ContentDynamicstate contentDynamicstate)
    {
        contentDynamicstate.setUpdateTime(DateUtils.getNowDate());
        return contentDynamicstateMapper.updateContentDynamicstate(contentDynamicstate);
    }

    /**
     * 批量删除内容管理-动态管理
     * 
     * @param ids 需要删除的内容管理-动态管理主键
     * @return 结果
     */
    @Override
    public int deleteContentDynamicstateByIds(Long[] ids)
    {
        return contentDynamicstateMapper.deleteContentDynamicstateByIds(ids);
    }

    /**
     * 删除内容管理-动态管理信息
     * 
     * @param id 内容管理-动态管理主键
     * @return 结果
     */
    @Override
    public int deleteContentDynamicstateById(Long id)
    {
        return contentDynamicstateMapper.deleteContentDynamicstateById(id);
    }

    @Override
    public List<AppUser> addUserList(String remark) {
        return appUserMapper.selectAppUserListLikeByNameOrAccount(remark);
    }
}
