package com.guoguoyun.web.controller.app.service;

import cn.hutool.core.lang.tree.Tree;
import com.guoguoyun.system.domain.UserComment;

import java.util.List;

public interface AppUserCommentService {

    /**
     * 添加评论
     * @param userComment
     */
    public void addComment(UserComment userComment);

    /**
     * 评论树
     * @param id
     * @return
     */
    public List<Tree<String>> commentTree(Long id,Integer type);
}
