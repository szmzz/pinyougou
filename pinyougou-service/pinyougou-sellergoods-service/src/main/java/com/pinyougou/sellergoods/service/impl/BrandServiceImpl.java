package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Transactional
@Service(interfaceName = "com.pinyougou.service.BrandService")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    //查询所有
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    //保存
    @Override
    public int save(Brand brand) {
        return brandMapper.insertSelective(brand);
    }

    //根据ID修改数据
    @Override
    public int update(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public PageResult findByPage(Brand brand, int page, int size) {
        PageInfo<Brand> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                brandMapper.findAll(brand);
            }
        });


        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public int delete(Long[] ids) {

        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        return brandMapper.deleteByExample(example);
    }

    @Override
    public List<Map<String, String>> findBrandList() {
        return brandMapper.findBrandList();
    }


}
