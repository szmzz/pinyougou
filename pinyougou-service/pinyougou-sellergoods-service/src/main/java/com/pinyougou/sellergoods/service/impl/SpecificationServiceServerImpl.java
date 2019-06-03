package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Transactional
@Service(interfaceName = "com.pinyougou.service.SpecificationService")
public class SpecificationServiceServerImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    //分页查询和搜索
    @Override
    public PageResult findByPage(Specification specification, int page, int size) {
        try {
            PageInfo<Specification> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    specificationMapper.findAll(specification);
                }
            });
            return new PageResult(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //新增规格和规格选项
    public void save(Specification specification) {
        try {
            specificationMapper.insertSelective(specification);

            if (specification.getSpecificationOptionList().size()>0) {
                specificationOptionMapper.save(specification);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //根据ID查询规格选项
    @Override
    public List<SpecificationOption> findSpecOption(Long specId) {
        try {
            SpecificationOption specificationOption = new SpecificationOption();
            specificationOption.setSpecId(specId);
            return specificationOptionMapper.select(specificationOption);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Specification specification) {
        try {
            //修改规格信息
            specificationMapper.updateByPrimaryKey(specification);
            List<SpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
            //修改规格选项信息 1先删除对应的规格选项,
            SpecificationOption specificationOption = new SpecificationOption();
            specificationOption.setSpecId(specification.getId());
            specificationOptionMapper.delete(specificationOption);
            //2再插入
            specificationOptionMapper.save(specification);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long[] ids) {
        try {
            //删除规格
            Example example = new Example(Specification.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("id", Arrays.asList(ids));
            specificationMapper.deleteByExample(example);

            for (Long id : ids) {
                //删除规格选项
                Example example1 = new Example(SpecificationOption.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("specId", id);
                specificationOptionMapper.deleteByExample(example1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, String>> findSpecificationList() {
        return specificationMapper.findSpecificationList();
    }
}
