package com.ethan.mall.admin.service;

import com.ethan.mall.model.CmsSubject;

import java.util.List;

/**
 * @author ethan
 * @Date 9:09 PM 2021/12/25
 * @Description 商品专题管理业务接口
 */
public interface ICmsSubjectService {

    /**
     * 获取全部商品专题
     * @return
     */
    List<CmsSubject> listAll();
}
