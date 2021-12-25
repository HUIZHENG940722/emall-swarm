package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.service.ICmsSubjectService;
import com.ethan.mall.mapper.CmsSubjectMapper;
import com.ethan.mall.model.CmsSubject;
import com.ethan.mall.model.CmsSubjectExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 9:11 PM 2021/12/25
 * @Description 商品专题管理业务类
 */
@Service
public class CmsSubjectService implements ICmsSubjectService {

    @Resource
    private CmsSubjectMapper subjectMapper;

    @Override
    public List<CmsSubject> listAll() {
        // 1 校验
        // 2 查询所有
        List<CmsSubject> subjectList = subjectMapper.selectByExample(new CmsSubjectExample());
        // 3 返回结果集
        return subjectList;
    }
}
