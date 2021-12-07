package com.ethan.mall.admin.dao;

import com.ethan.mall.model.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 10:12 PM 2021/12/7
 * @Description 专题商品关联数据访问扩展
 */
public interface CmsSubjectProductRelationDao {
    /**
     * 插入专题商品关联数据集
     * @param cmsSubjectProductRelationList
     * @return
     */
    int insertList(@Param("list")List<CmsSubjectProductRelation> cmsSubjectProductRelationList);
}
