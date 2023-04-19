package com.practice.controller;

import com.practice.auth.CustomUserDetails;
import com.practice.domain.InventoryDTO;
import com.practice.service.InventoryService;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private MemberService memberService;

    //재고목록페이지
    @GetMapping("/inventoryList")
    public String inventoryListPage(Model model) {
        List<InventoryDTO> list = inventoryService.selectInventoryList();
        model.addAttribute("list", list);
        return "/inventory/inventoryList";
    }

    //재고등록페이지
    @GetMapping("/inventoryWrite")
    public String inventoryWritePage() {
        return "/inventory/inventoryWrite";
    }

    //재고등록 Action
    @PostMapping("/inventoryWriteAjax")
    @ResponseBody //Ajax 사용시
    public void inventoryWriteAction(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        CustomUserDetails customUserDetails = memberService.getMemberById(id);
        int no = customUserDetails.getNo();

        int subject = Integer.parseInt(request.getParameter("subject"));
        String buyDt = request.getParameter("buyDt");
        String buyer = request.getParameter("buyer");
        String amount = request.getParameter("amount");

        InventoryDTO dto = new InventoryDTO();
        dto.setNo(no);
        dto.setSubject(subject);
        dto.setBuyDt(buyDt);
        dto.setBuyer(buyer);
        dto.setAmount(amount);
        inventoryService.insertInventory(dto);
    }

    //재고 상세페이지 & 수정페이지
    @PostMapping("/inventoryUpdate")
    public String inventoryUpdatePage(HttpServletRequest request, Model model) {
        int subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
        InventoryDTO dto = inventoryService.getInventoryListBySubjectNo(subjectNo);
        model.addAttribute("dto", dto);
        return "/inventory/inventoryUpdate";
    }

    //재고 삭제하기
    @PostMapping("/inventoryDeleteAjax")
    @ResponseBody
    public void inventoryDeleteAction(HttpServletRequest request, Model model) {
        int subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
        inventoryService.deleteInventory(subjectNo);
    }

    //재고 수정하기
    @PostMapping("/inventoryUpdateAjax")
    @ResponseBody
    public void inventoryUpdateAction(HttpServletRequest request, Model model) {
        int subject = Integer.parseInt(request.getParameter("subject"));
        int subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
        String buyDt = request.getParameter("buyDt");
        String buyer = request.getParameter("buyer");
        String amount = request.getParameter("amount");

        InventoryDTO dto = new InventoryDTO();
        dto.setSubject(subject);
        dto.setSubjectNo(subjectNo);
        dto.setBuyDt(buyDt);
        dto.setBuyer(buyer);
        dto.setAmount(amount);

        inventoryService.updateInventory(dto);
    }

    //재고구매페이지
    @GetMapping("/inventoryShop")
    public String inventoryShopPage() {
        return "/inventory/inventoryShop";
    }
}
