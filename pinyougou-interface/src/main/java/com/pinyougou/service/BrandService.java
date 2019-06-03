package com.pinyougou.service;

import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
    /** 查询所有品牌 */
    List<Brand> findAll();
    //保存
    int save(Brand brand);

    //根据ID修改数据
    int update(Brand brand);

    PageResult findByPage(Brand brand, int page, int size);

    int delete(Long[] ids);

    List<Map<String, String>> findBrandList();
}
