package com.guoguoyun.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;

/**
 * app版本对象 app_version
 * 
 * @author ruoyi
 * @date 2022-02-24
 */
@Accessors(chain = true)
@Data
public class AppVersion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionNum;

    /** apk包下载地址 */
    @Excel(name = "apk包下载地址")
    private String apkUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVersionNum(String versionNum) 
    {
        this.versionNum = versionNum;
    }

    public String getVersionNum() 
    {
        return versionNum;
    }
    public void setApkUrl(String apkUrl) 
    {
        this.apkUrl = apkUrl;
    }

    public String getApkUrl() 
    {
        return apkUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("versionNum", getVersionNum())
            .append("apkUrl", getApkUrl())
            .toString();
    }
}
