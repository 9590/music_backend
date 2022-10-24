package com.guoguoyun.system.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guoguoyun.common.utils.DateUtils;
import com.guoguoyun.system.domain.AppVersion;
import com.guoguoyun.system.domain.UserContributeRelation;
import com.guoguoyun.system.mapper.AppVersionMapper;
import com.guoguoyun.system.mapper.UserContributeRelationMapper;
import com.guoguoyun.system.service.IAppVersionService;
import com.guoguoyun.system.service.IUserContributeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * app版本Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class UserContributeRelationServiceImpl extends ServiceImpl<UserContributeRelationMapper, UserContributeRelation> implements IUserContributeRelationService
{

}
