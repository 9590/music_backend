package com.guoguoyun.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.guoguoyun.common.constant.MusicCode;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.*;
import com.guoguoyun.system.response.OperationAdvertisingResponse;
import com.guoguoyun.system.service.IAlbumService;
import com.guoguoyun.system.service.IContentArticleService;
import com.guoguoyun.system.service.IShoppingCommodityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import com.guoguoyun.system.mapper.ClassifyMapper;
import com.guoguoyun.system.service.IClassifyService;

/**
 * 商城管理-分类管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-12
 */
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements IClassifyService
{
    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private IShoppingCommodityService shoppingCommodityService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private IContentArticleService contentArticleService;

    /**
     * 查询商城管理-分类管理
     * 
     * @param id 商城管理-分类管理主键
     * @return 商城管理-分类管理
     */
    @Override
    public Classify selectClassifyById(Long id)
    {
        return classifyMapper.selectClassifyById(id);
    }

    /**
     * 查询商城管理-分类管理列表
     * 
     * @param classify 商城管理-分类管理
     * @return 商城管理-分类管理
     */
    @Override
    public List<Classify> selectClassifyList(Classify classify)
    {
        return classifyMapper.selectClassifyList(classify);
    }

    /**
     * 新增商城管理-分类管理
     * 
     * @param classify 商城管理-分类管理
     * @return 结果
     */
    @Override
    public int insertClassify(Classify classify)
    {

        QueryWrapper<Classify> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Classify::getClassifyNumber,classify.getClassifyNumber());
        Classify classify1 = this.getOne(wrapper);
        if (ObjectUtil.isNotEmpty(classify1)){
            throw new SecurityException(MusicCode.ERROR_NUMBER_REOETITION);
        }

        classify.setCreateTime(DateUtils.getNowDate());
        classify.setId(IdUtil.getSnowflakeNextId());
        return classifyMapper.insertClassify(classify);
    }

    /**
     * 修改商城管理-分类管理
     * 
     * @param classify 商城管理-分类管理
     * @return 结果
     */
    @Override
    public int updateClassify(Classify classify)
    {
        classify.setUpdateTime(DateUtils.getNowDate());
        return classifyMapper.updateClassify(classify);
    }

    /**
     * 批量删除商城管理-分类管理
     * 
     * @param ids 需要删除的商城管理-分类管理主键
     * @return 结果
     */
    @Override
    public int deleteClassifyByIds(Long[] ids)
    {
        Classify byId = getById(ids[0]);
        switch (byId.getClassifyType()){
            /* 商品 */
            case 1:
                QueryWrapper<ShoppingCommodity> shoppingCommodityQueryWrapper = new QueryWrapper<>();
                shoppingCommodityQueryWrapper.lambda().eq(ShoppingCommodity::getClassifyId,byId.getId());
                List<ShoppingCommodity> list = shoppingCommodityService.list(shoppingCommodityQueryWrapper);
                if (list.size() != 0){
                    throw new SecurityException("分类已被使用，不得删除，只可编辑！");
                }
                break;
            /* 专辑 */
            case 2:
                QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
                albumQueryWrapper.lambda().eq(Album::getAlbumClassifyId,byId.getId());
                albumQueryWrapper.lambda().eq(Album::getDeleteType,0);
                List<Album> albumList = albumService.list(albumQueryWrapper);
                if (albumList.size() != 0){
                    throw new SecurityException("分类已被使用，不得删除，只可编辑！");
                }
                break;
            /* 文章 */
            case 3:
                QueryWrapper<ContentArticle> articleQueryWrapper = new QueryWrapper<>();
                articleQueryWrapper.lambda().eq(ContentArticle::getArticleType,byId.getId());
                List<ContentArticle> articleList = contentArticleService.list(articleQueryWrapper);
                if (articleList.size() != 0){
                    throw new SecurityException("分类已被使用，不得删除，只可编辑！");
                }
                break;
            default:break;
        }
        return classifyMapper.deleteClassifyByIds(ids);
    }

    /**
     * 删除商城管理-分类管理信息
     * 
     * @param id 商城管理-分类管理主键
     * @return 结果
     */
    @Override
    public int deleteClassifyById(Long id)
    {
        return classifyMapper.deleteClassifyById(id);
    }

    @Override
    public PageInfo treeList(Boolean type,Classify classify) {
        List<Classify> classifies = new ArrayList<>();
        if (type){
            classify.setClassifyType(2);
            classify.setClassifyParent(0l);
            classifies = classifyMapper.selectClassifyList(classify);

            List<Classify> list = new ArrayList<>();
            classifies.forEach(t -> {
                Classify classifyList = new Classify();
                classifyList.setClassifyParent(t.getId());
                List<Classify> list1 = classifyMapper.selectClassifyList(classifyList);
                list.addAll(list1);
            });

            classifies.addAll(list);

        }else {
            QueryWrapper<Classify> wrapper = new QueryWrapper<>();
            wrapper.lambda().ne(Classify::getClassifyType,3);
            classifies = this.list(wrapper);
        }

        List<TreeNode<String>> collect = classifies.stream().map(t -> {
            Map<String, Object> map = new HashMap<>();
            map.put("number",t.getClassifyNumber());
            TreeNode<String> treeNode = new TreeNode<String>().setId(t.getId() + "")
                    .setName(t.getClassifyName())
                    .setParentId(t.getClassifyParent().toString())
                    .setWeight(t.getId() + "")
                    .setExtra(map);

            return treeNode;
        }).collect(Collectors.toList());

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setChildrenKey("children");
        // 最大递归深度
        treeNodeConfig.setDeep(6);
        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(collect, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setName(treeNode.getName());
                    tree.putExtra("number", treeNode.getExtra().get("number"));
                    tree.setWeight(treeNode.getWeight());
                });

        PageInfo<Classify> page = new PageInfo<>(classifies);
        PageInfo<Tree<String>> pageInfo = new PageInfo<>(treeNodes);
        BeanUtils.copyProperties(page ,pageInfo );
        pageInfo.setList(treeNodes);
        pageInfo.setTotal(treeNodes.size());
        return pageInfo;
    }

    @Override
    public List<Tree<String>> treeList(Integer type) {
        List<Classify> classifies = new ArrayList<>();
        if (type == 1){
            Classify classify = new Classify();
            classify.setClassifyType(2);
            classifies = classifyMapper.selectClassifyList(classify);
        }else {
            QueryWrapper<Classify> wrapper = new QueryWrapper<>();
            wrapper.lambda().ne(Classify::getClassifyType,3);
            classifies = this.list(wrapper);
        }

        List<TreeNode<String>> collect = classifies.stream().map(t -> {
            Map<String, Object> map = new HashMap<>();
            map.put("number",t.getClassifyNumber());
            TreeNode<String> treeNode = new TreeNode<String>().setId(t.getId() + "")
                    .setName(t.getClassifyName())
                    .setParentId(t.getClassifyParent().toString())
                    .setWeight(t.getId() + "")
                    .setExtra(map);

            return treeNode;
        }).collect(Collectors.toList());

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setChildrenKey("children");
        // 最大递归深度
        treeNodeConfig.setDeep(6);
        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(collect, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setName(treeNode.getName());
                    tree.putExtra("number", treeNode.getExtra().get("number"));
                    tree.setWeight(treeNode.getWeight());
                });
        return treeNodes;
    }
}
