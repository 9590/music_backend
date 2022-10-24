package com.guoguoyun.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.enums.ShopIsTopType;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.mapper.AlbumMapper;
import com.guoguoyun.system.params.ContentArticleParams;
import com.guoguoyun.system.response.ContentArticleResponse;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.service.IArticleAlbumService;
import com.guoguoyun.system.service.IClassifyService;
import com.guoguoyun.system.service.IContentArticleTagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.ContentArticleMapper;
import com.guoguoyun.system.service.IContentArticleService;

/**
 * 内容管理-文章管理Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class ContentArticleServiceImpl extends ServiceImpl<ContentArticleMapper, ContentArticle> implements IContentArticleService {
    @Autowired
    private ContentArticleMapper contentArticleMapper;

    @Autowired
    private IContentArticleTagService contentArticleTagService;

    @Autowired
    private IArticleAlbumService articleAlbumService;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private IClassifyService classifyService;

    /**
     * 查询内容管理-文章管理
     *
     * @param id 内容管理-文章管理主键
     * @return 内容管理-文章管理
     */
    @Override
    public ContentArticleResponse selectContentArticleById(Long id) {
        ContentArticle contentArticle = contentArticleMapper.selectContentArticleById(id);
        ContentArticleResponse contentArticleResponse = BeanUtil.toBean(contentArticle, ContentArticleResponse.class);
        if (ObjectUtil.isNotEmpty(contentArticle.getArticleTag())){
            contentArticleResponse.setArticleTag(JSONArray.parseArray(contentArticle.getArticleTag(), String.class));
        }
        List<Album> albums = albumMapper.selectAlbumListByArticleId(contentArticle.getId());
        contentArticleResponse.setAlbumIds(albums);
        return contentArticleResponse;
    }

    /**
     * 查询内容管理-文章管理列表
     *
     * @param contentArticle 内容管理-文章管理
     * @return 内容管理-文章管理
     */
    @Override
    public PageInfo selectContentArticleList(ContentArticle contentArticle) {
        List<ContentArticle> contentArticles = contentArticleMapper.selectContentArticleList(contentArticle);
        List<ContentArticleResponse> contentArticleResponses = contentArticles.stream().map(t -> {
            ContentArticleResponse contentArticleResponse = BeanUtil.toBean(t, ContentArticleResponse.class);
            if (ObjectUtil.isNotEmpty(t.getArticleTag())){
                contentArticleResponse.setArticleTag(JSONArray.parseArray(t.getArticleTag(), String.class));
            }
            contentArticleResponse.setArticleIsTopName(ShopIsTopType.getEnumType(t.getArticleIsTop()).getInfo());
            contentArticleResponse.setArticleTypeName(classifyService.getById(t.getArticleType()).getClassifyName());
            List<Album> albums = albumMapper.selectAlbumListByArticleId(t.getId());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < albums.size(); i++) {
                if (i == albums.size() - 1) {
                    stringBuffer.append(albums.get(i).getAlbumName());
                } else {
                    stringBuffer.append(albums.get(i).getAlbumName() + ",");
                }
            }
            contentArticleResponse.setAlbumNames(stringBuffer.toString());
            return contentArticleResponse;
        }).collect(Collectors.toList());

        PageInfo<ContentArticle> page = new PageInfo<>(contentArticles);
        PageInfo<ContentArticleResponse> pageInfo = new PageInfo<>(contentArticleResponses);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(contentArticleResponses);
        return pageInfo;
    }

    /**
     * 新增内容管理-文章管理
     *
     * @param contentArticle 内容管理-文章管理
     * @return 结果
     */
    @Override
    public int insertContentArticle(ContentArticle contentArticle) {
        contentArticle.setCreateTime(DateUtils.getNowDate());
        contentArticle.setId(IdUtil.getSnowflakeNextId());
        return contentArticleMapper.insertContentArticle(contentArticle);
    }

    @Override
    public void insertContentArticle(ContentArticleParams contentArticleParams) {
        ContentArticle contentArticle = BeanUtil.toBean(contentArticleParams, ContentArticle.class);
        contentArticle.setCreateTime(DateUtils.getNowDate());
        contentArticle.setId(IdUtil.getSnowflakeNextId());

        if (contentArticleParams.getArticleTag().size() != 0) {
            String tsgs = JSONArray.toJSON(contentArticleParams.getArticleTag()).toString();
            contentArticle.setArticleTag(tsgs);
            this.save(contentArticle);
            contentArticleParams.getArticleTag().forEach(t -> {
                QueryWrapper<ContentArticleTag> wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(ContentArticleTag::getUserId, contentArticleParams.getUserId());
                wrapper.lambda().eq(ContentArticleTag::getTagName, t);
                ContentArticleTag contentArticleTagServiceOne = contentArticleTagService.getOne(wrapper);
                if (ObjectUtil.isEmpty(contentArticleTagServiceOne)) {
                    ContentArticleTag contentArticleTag = new ContentArticleTag();
                    contentArticleTag.setTagName(t);
                    contentArticleTag.setUserId(contentArticleParams.getUserId());
                    contentArticleTagService.insertContentArticleTag(contentArticleTag);
                }
            });
        }


        if (contentArticleParams.getAlbumIds().size() != 0) {
            QueryWrapper<ArticleAlbum> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(ArticleAlbum::getArticleId, contentArticle.getId());
            articleAlbumService.remove(wrapper);
            contentArticleParams.getAlbumIds().forEach(t -> {
                ArticleAlbum articleAlbum = new ArticleAlbum();
                articleAlbum.setArticleId(contentArticle.getId());
                articleAlbum.setAlbumId(t);
                articleAlbumService.insertArticleAlbum(articleAlbum);

            });
        }
    }

    /**
     * 修改内容管理-文章管理
     *
     * @param contentArticle 内容管理-文章管理
     * @return 结果
     */
    @Override
    public int updateContentArticle(ContentArticle contentArticle) {
        contentArticle.setUpdateTime(DateUtils.getNowDate());
        return contentArticleMapper.updateContentArticle(contentArticle);
    }

    @Override
    public void updateContentArticle(ContentArticleParams contentArticleParams) {
        ContentArticle contentArticle = BeanUtil.toBean(contentArticleParams, ContentArticle.class);
        contentArticle.setUpdateTime(DateUtils.getNowDate());
        if (contentArticleParams.getArticleTag().size() != 0) {
            String tsgs = JSONArray.toJSON(contentArticleParams.getArticleTag()).toString();
            contentArticle.setArticleTag(tsgs);
            this.updateById(contentArticle);
            contentArticleParams.getArticleTag().forEach(t -> {
                QueryWrapper<ContentArticleTag> wrapper = new QueryWrapper<>();
                wrapper.lambda().eq(ContentArticleTag::getUserId, contentArticleParams.getUserId());
                wrapper.lambda().eq(ContentArticleTag::getTagName, t);
                ContentArticleTag contentArticleTagServiceOne = contentArticleTagService.getOne(wrapper);
                if (ObjectUtil.isEmpty(contentArticleTagServiceOne)) {
                    ContentArticleTag contentArticleTag = new ContentArticleTag();
                    contentArticleTag.setTagName(t);
                    contentArticleTag.setUserId(contentArticleParams.getUserId());
                    contentArticleTagService.insertContentArticleTag(contentArticleTag);
                }
            });
        }


        if (contentArticleParams.getAlbumIds().size() != 0) {
            QueryWrapper<ArticleAlbum> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(ArticleAlbum::getArticleId, contentArticle.getId());
            articleAlbumService.remove(wrapper);
            contentArticleParams.getAlbumIds().forEach(t -> {
                ArticleAlbum articleAlbum = new ArticleAlbum();
                articleAlbum.setArticleId(contentArticle.getId());
                articleAlbum.setAlbumId(t);
                articleAlbumService.insertArticleAlbum(articleAlbum);
            });
        }
    }

    /**
     * 批量删除内容管理-文章管理
     *
     * @param ids 需要删除的内容管理-文章管理主键
     * @return 结果
     */
    @Override
    public int deleteContentArticleByIds(Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            QueryWrapper<ArticleAlbum> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(ArticleAlbum::getArticleId, ids[i]);
            articleAlbumService.remove(wrapper);
        }

        return contentArticleMapper.deleteContentArticleByIds(ids);
    }

    /**
     * 删除内容管理-文章管理信息
     *
     * @param id 内容管理-文章管理主键
     * @return 结果
     */
    @Override
    public int deleteContentArticleById(Long id) {
        return contentArticleMapper.deleteContentArticleById(id);
    }

    @Override
    public int updateTop(Long id) {
        return contentArticleMapper.updateTop(id);
    }
}
