package com.pinyougou.service;

import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Seller;

import java.io.Serializable;

public interface SellerService {

    void save(Seller seller);

    PageResult findByPage(Seller seller, Integer page, Integer rows);

    void updateStatus(String sellerId, String status);

    Seller findOne(Serializable username);
}
