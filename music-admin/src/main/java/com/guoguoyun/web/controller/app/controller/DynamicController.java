package com.guoguoyun.web.controller.app.controller;


import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.system.domain.UserFollow;
import com.guoguoyun.system.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app/Dynamic")
public class DynamicController extends BaseController {



    @Autowired
    DynamicService dynamicService;


    /**
     * 查询用户关注
     *
     * @param id 用户关注主键
     * @return 用户关注
     *   status:0 表示未关注
     *   status：1 表示关注
     */

    @GetMapping("/electUserFollowById")
    public int selectUserFollowById(Long id){

        int status = 1;

       UserFollow userFollow  =  dynamicService.selectUserFollowById(id);

       if (userFollow==null){

           status =0;
       }
        return status;
    }

    /**
     * 查询用户关注列表
     *
     * @param userFollow 用户关注
     * @return 用户关注集合
     */
    @GetMapping("/selectUserFollowList")
    public List<UserFollow> selectUserFollowList(@RequestBody UserFollow userFollow){


        return  null;
    }

    /**
     * 新增用户关注
     *
     * @param userFollow 用户关注
     * @return 1:成功
     *         0：失败
     */
    @GetMapping("/insertUserFollow")
    public int insertUserFollow(@RequestBody UserFollow userFollow){

        return dynamicService.insertUserFollow(userFollow);
    }

    /**
     * 修改用户关注
     *
     * @param userFollow 用户关注
     * @return 结果
     */
    @GetMapping("/updateUserFollow")
    public int updateUserFollow(@RequestBody UserFollow userFollow){

        return dynamicService.updateUserFollow(userFollow);


    }

    /**
     * 删除用户关注
     *
     * @param id 用户关注主键
     * @return 结果
     */
    @GetMapping("/deleteUserFollowById")
    public int deleteUserFollowById(Long id){

        return dynamicService.deleteUserFollowById(id);

    }

    /**
     * 批量删除用户关注
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    @GetMapping("/deleteUserFellowByIds")
    public int deleteUserFollowByIds(Long[] ids){

        int status = 0;

        status = dynamicService.deleteUserFollowByIds(ids);
        if (status == ids.length){

            status =1;

        }
        return status;
    }

}
