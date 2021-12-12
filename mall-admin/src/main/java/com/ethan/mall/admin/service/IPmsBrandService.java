package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.PmsBrandCreateParam;
import com.ethan.mall.model.PmsBrand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ethan
 * @Date 7:26 PM 2021/12/5
 * @Description 商品品牌管理业务接口
 */
@Service
public interface IPmsBrandService {
    /**
     * 品牌名称分页查询品牌列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> getList(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 创建商品品牌
     * @param brandCreateParam
     * @return
     */
    int create(PmsBrandCreateParam brandCreateParam);
}
