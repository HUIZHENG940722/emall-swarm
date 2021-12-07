package com.ethan.mall.admin.dao;

import com.ethan.mall.model.CmsPreferenceAreaProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 10:25 PM 2021/12/7
 * @Description 优选商品关联数据访问扩展
 */
public interface CmsPreferenceAreaProductRelationDao {
    /**
     * 插入优选关联数据集
     * @param preferenceAreaProductRelationList
     * @return
     */
    int insertList(@Param("list")List<CmsPreferenceAreaProductRelation> preferenceAreaProductRelationList);
}
