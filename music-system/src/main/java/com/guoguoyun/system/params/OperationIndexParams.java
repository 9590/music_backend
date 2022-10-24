package com.guoguoyun.system.params;

import com.guoguoyun.common.annotation.Excel;
import com.guoguoyun.common.core.domain.BaseEntity;
import com.guoguoyun.system.domain.OperationIndex;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 运营管理-首页管理对象 operation_index
 * 
 * @author ruoyi
 * @date 2022-02-23
 */
@Accessors(chain = true)
@Data
public class OperationIndexParams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 精选专辑
     */
   private List<OperationIndex> choicenessAlbums;

    /**
     * 热门曲目
     */
   private List<OperationIndex> hotSongs;
}
