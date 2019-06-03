package com.pinyougou.pojo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "tb_specification")
public class Specification implements Serializable {

    private static final long serialVersionUID = -972374525762485421L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spec_name")
    private String specName;

    @Transient  //代表不是此表中的列
    private List<SpecificationOption> specificationOptionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
