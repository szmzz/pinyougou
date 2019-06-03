package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference(timeout = 10000)
    private SpecificationService specificationService;


    //返回分页列表和搜索数据
    @GetMapping("findByPage")
    private PageResult findByPage(Specification specification, int page, int size) {
        if (specification != null && StringUtils.isNoneBlank(specification.getSpecName())) {
            try {
                specification.setSpecName(new String(specification.getSpecName().getBytes("ISO8859-1"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return specificationService.findByPage(specification, page, size);
    }


    //返回分页列表和搜索数据
    @GetMapping("findSpecificationList")
    private List<Map<String,String>> findSpecificationList() {
        return specificationService.findSpecificationList();
    }

    //保存
    @PostMapping("save")
    private boolean save(@RequestBody Specification specification) {
        try {
            specificationService.save(specification);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //根据 规格的ID  查询  规格选项列表
    @GetMapping("findSpecOption")
    private List<SpecificationOption> findSpecOption(Long id) {
        return specificationService.findSpecOption(id);
    }

    //修改规格和规格选项
    @PostMapping("/update")
    private boolean update(@RequestBody Specification specification) {
        try {
            specificationService.update(specification);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @GetMapping("delete")
    private boolean delete(Long[] ids) {
        try {
            specificationService.delete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
