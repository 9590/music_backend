package com.guoguoyun.web.controller.app.service;

import com.guoguoyun.system.params.app.AppSearchParams;

import java.util.Map;

public interface AppSearchLogService {


    /**
     * 搜索热词
     * @param userId
     * @return
     */
    public Map<String,Object> searchWordLog(Long userId);

    /**
     * 搜索
     * @param appSearchParams
     * @return
     */
    public Map<String,Object> searchResult(AppSearchParams appSearchParams);

    public void  removeUserSearchLog (Long userId);
}
