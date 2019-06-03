package com.pinyougou.service;

import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.TypeTemplate;

public interface TypeTemplateService {
    PageResult findByPage(TypeTemplate typeTemplate, int page, int size);
    void save(TypeTemplate typeTemplate);
    void update(TypeTemplate typeTemplate);
    void delete(Long[] ids);
}
