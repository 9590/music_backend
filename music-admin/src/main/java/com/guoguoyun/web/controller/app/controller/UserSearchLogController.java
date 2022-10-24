package com.guoguoyun.web.controller.app.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.guoguoyun.framework.web.service.TokenService;
import com.guoguoyun.system.params.BaseParam;
import com.guoguoyun.system.params.app.AppSearchParams;
import com.guoguoyun.web.controller.app.service.AppSearchLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guoguoyun.common.annotation.Log;
import com.guoguoyun.common.core.controller.BaseController;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.common.enums.BusinessType;
import com.guoguoyun.system.domain.UserSearchLog;
import com.guoguoyun.system.service.IUserSearchLogService;
import com.guoguoyun.common.utils.poi.ExcelUtil;
import com.guoguoyun.common.core.page.TableDataInfo;

/**
 * 搜索记录Controller
 * 
 * @author ruoyi
 * @date 2022-06-15
 */
@RestController
@RequestMapping("/app/userSearchLog")
public class UserSearchLogController extends BaseController
{


    @Autowired
    private AppSearchLogService appSearchLogService;

    @Autowired
    private TokenService tokenService;

    /**
     * 搜索结果
     * @return
     */
    @GetMapping("/searchThing")
    public AjaxResult searchResult(@Validated(BaseParam.list.class) AppSearchParams appSearchParams){
        Map<String, Object> map = appSearchLogService.searchResult(appSearchParams);
        return AjaxResult.success(map);
    }


    /**
     * 搜索词语展示
     * @return
     */
    @GetMapping("/searchWordLog")
    public AjaxResult searchWordLog(Long userId){
        Map<String, Object> map = appSearchLogService.searchWordLog(userId);
        return AjaxResult.success(map);
    }

    @PostMapping("/removeUserSearchLog")
    public AjaxResult removeUserSearchLog(){
        appSearchLogService.removeUserSearchLog(tokenService.getLoginUser().getUserId());
        return AjaxResult.success();
    }
}
