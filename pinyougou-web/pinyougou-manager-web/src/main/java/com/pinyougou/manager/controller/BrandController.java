package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference(timeout = 10000)
    private BrandService brandService;


    //查询所有
    @GetMapping("/findAll")
    private List<Brand> findAll() {
        return brandService.findAll();
    }

    //保存
    @PostMapping("/save")
    private boolean save(@RequestBody Brand brand) {
        try {
            int save = brandService.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //根据ID修改数据
    @PostMapping("/update")
    private boolean update(@RequestBody Brand brand) {
        try {
            int save = brandService.update(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //分页和条件查询
    @GetMapping("findByPage")
    private PageResult findByPage(Brand brand, int page, int size) {
        /** GET请求中文转码 */
        if (brand != null && StringUtils.isNoneBlank(brand.getName())) {
            try {
                brand.setName(new String(brand.getName().getBytes("ISO8859-1"), "UTF-8"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return brandService.findByPage(brand, page, size);
    }

    //删除
    @GetMapping("delete")
    private boolean delete(Long[] ids) {
        int a = brandService.delete(ids);
        return true;

    }


    @GetMapping("findBrandList")
    private List<Map<String,String>> findBrandList(){
       return brandService.findBrandList();
    }




}
