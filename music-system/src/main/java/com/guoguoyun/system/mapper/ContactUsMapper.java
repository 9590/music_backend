package com.guoguoyun.system.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoguoyun.system.domain.ContactUs;
import org.springframework.stereotype.Repository;

/**
 * 联系我们Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-11
 */
@Repository
public interface ContactUsMapper extends BaseMapper<ContactUs>
{
    /**
     * 查询联系我们
     * 
     * @param id 联系我们主键
     * @return 联系我们
     */
    public ContactUs selectContactUsById(Long id);

    /**
     * 查询联系我们列表
     * 
     * @param contactUs 联系我们
     * @return 联系我们集合
     */
    public List<ContactUs> selectContactUsList(ContactUs contactUs);

    /**
     * 新增联系我们
     * 
     * @param contactUs 联系我们
     * @return 结果
     */
    public int insertContactUs(ContactUs contactUs);

    /**
     * 修改联系我们
     * 
     * @param contactUs 联系我们
     * @return 结果
     */
    public int updateContactUs(ContactUs contactUs);

    /**
     * 删除联系我们
     * 
     * @param id 联系我们主键
     * @return 结果
     */
    public int deleteContactUsById(Long id);

    /**
     * 批量删除联系我们
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContactUsByIds(Long[] ids);
}
