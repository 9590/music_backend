package com.guoguoyun.web.controller.app.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.system.domain.AppUser;
import com.guoguoyun.system.domain.UserComment;
import com.guoguoyun.system.service.IAppUserService;
import com.guoguoyun.system.service.IUserCommentService;
import com.guoguoyun.web.controller.app.service.AppUserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppUserCommentServiceImpl implements AppUserCommentService {

    @Autowired
    private IUserCommentService userCommentService;

    @Autowired
    private IAppUserService appUserService;


    /**
     * 添加评论
     * @param userComment
     */
    @Override
    public void addComment(UserComment userComment) {
        if (ObjectUtil.isNotEmpty(userComment.getParentId())){
            UserComment comment = userCommentService.getById(userComment.getParentId());
            if (comment.getParentId() != 0){
                throw new SecurityException("不可再进行回复");
            }
        }
        userCommentService.insertUserComment(userComment);
    }

    /**
     * 评论树
     * @param id
     * @return
     */
    @Override
    public List<Tree<String>> commentTree(Long id,Integer type) {
        QueryWrapper<UserComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.lambda().eq(UserComment::getArticleId,id);
        commentQueryWrapper.lambda().eq(UserComment::getType,type);
        List<UserComment> commentList = userCommentService.list(commentQueryWrapper);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TreeNode<String>> treeNodeList = commentList.stream().map(t -> {
            Map<String, Object> map = new HashMap<>();
            map.put("content", t.getContent());
            map.put("createTime", sdf.format(t.getCreateTime()));
            AppUser appUser = appUserService.getById(t.getUserId());
            TreeNode<String> treeNode = new TreeNode<String>().setId(t.getId() + "")
                    .setName(appUser.getUserName())
                    .setParentId(t.getParentId().toString())
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
        List<Tree<String>> treeNodes = TreeUtil.build(treeNodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setName(treeNode.getName());
                    tree.putExtra("content", treeNode.getExtra().get("content"));
                    tree.putExtra("createTime", treeNode.getExtra().get("createTime"));
                    tree.setWeight(treeNode.getWeight());
                });
        return treeNodes;
    }
}
