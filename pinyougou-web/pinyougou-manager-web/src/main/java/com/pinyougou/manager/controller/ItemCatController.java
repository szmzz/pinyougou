package com.pinyougou.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.ItemCat;
import com.pinyougou.service.ItemCatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference(timeout = 10000)
    private ItemCatService itemCatService;

    //根据父类的ID查询分类
    @GetMapping("findItemCatByParentId")
    private List<ItemCat> findItemCatByParentId(long id){
        return itemCatService.findItemCatByParentId(id);
    }


    //根据父类的ID查询分类
    @GetMapping("save")
    private List<ItemCat> save(long id){
        return itemCatService.findItemCatByParentId(id);
    }

}
