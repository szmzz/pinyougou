package com.pinyougou.service;

import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;

import java.util.List;
import java.util.Map;

public interface SpecificationService {

    PageResult findByPage(Specification specification, int page, int size);

    void save(Specification specification);

    List<SpecificationOption> findSpecOption(Long specId);

    void update(Specification specification);

    void delete(Long[] ids);

    List<Map<String,String>> findSpecificationList();
}
