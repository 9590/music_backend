package com.guoguoyun.system.service.impl;

import com.guoguoyun.system.domain.UserFollow;
import com.guoguoyun.system.mapper.UserFollowMapper;
import com.guoguoyun.system.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class DynamicServiceImpl implements DynamicService {


    @Autowired
    UserFollowMapper userFollowMapper;




    @Override
    public UserFollow selectUserFollowById(Long id) {

          UserFollow  userFollow = userFollowMapper.selectUserFollowById(id);
          return  userFollow;
    }

    @Override
    public List<UserFollow> selectUserFollowList(UserFollow userFollow) {

         List<UserFollow> list = userFollowMapper.selectUserFollowList(userFollow);
        return list;
    }

    @Override
    public int insertUserFollow(UserFollow userFollow) {

        int status =  userFollowMapper.insertUserFollow(userFollow);
        return status;
    }

    @Override
    public int updateUserFollow(UserFollow userFollow) {
        int status  = userFollowMapper.updateUserFollow(userFollow);
        return status;
    }

    @Override
    public int deleteUserFollowById(Long id) {
        int status = userFollowMapper.deleteUserFollowById(id);

        return status;
    }


    @Override
    public int deleteUserFollowByIds(Long[] ids) {

        int status = userFollowMapper.deleteUserFollowByIds(ids);

        return status;
    }
}
