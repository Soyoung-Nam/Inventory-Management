package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {

    //재고목록페이지
    @GetMapping("/inventoryList")
    public String inventoryListPage() {
        return "/inventory/inventoryList";
    }

    //재고구매페이지
    @GetMapping("/inventoryShop")
    public String inventoryShopPage() {
        return "/inventory/inventoryShop";
    }
}
