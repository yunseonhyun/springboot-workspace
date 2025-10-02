package com.thejoeun.TheMall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class 페이지이동Controller {

    @GetMapping("/good/add")
    public String 상품등록페이지(){
        return "goodsRegister";
    }

    @GetMapping("/goodsList")
    public String 상품목록페이지(){
        return "goodsList";
    }

    @GetMapping("/users/List")
    public String 유저목록조회(){
        return "사용자목록";
    }
}
