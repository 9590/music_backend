package com.guoguoyun.web.controller.common;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guoguoyun.common.core.domain.AjaxResult;
import com.guoguoyun.system.domain.City;
import com.guoguoyun.system.service.ICityService;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private ICityService cityService;


    @GetMapping("/common/provinceList")
    public AjaxResult provinceList(){
        return AjaxResult.success(cityService.list(new QueryWrapper<City>().lambda().eq(City::getParentId,0)));
    }

    @GetMapping("/common/cityList")
    public AjaxResult cityList(Long id){
        return AjaxResult.success(cityService.list(new QueryWrapper<City>().lambda().eq(City::getParentId,id)));
    }

}
