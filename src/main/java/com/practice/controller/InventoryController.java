package com.practice.controller;

import com.practice.auth.CustomUserDetails;
import com.practice.domain.InventoryDTO;
import com.practice.domain.PagingResponse;
import com.practice.domain.SearchDTO;
import com.practice.service.InventoryService;
import com.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private MemberService memberService;

    //재고목록페이지
    @GetMapping("/inventoryList")
    public String inventoryListPage(@ModelAttribute("dto") SearchDTO dto, Model model, HttpServletRequest request) { //SearchDTO값을 dto이름으로 담는다.
        String keyword = request.getParameter("keyword");
        String result;

        if(keyword != "" && keyword != null) { //switch문 사용할때 null값 예외가 필요하다.
            switch (keyword) {
                case "컴퓨터":
                    result = "1";
                    break;
                case "모니터":
                    result = "2";
                    break;
                case "키보드":
                    result = "3";
                    break;
                case "마우스":
                    result = "4";
                    break;
                case "VGA":
                    result = "5";
                    break;
                case "스피커":
                    result = "6";
                    break;
                default:
                    result = keyword;
            }
            dto.setKeyword(result);
        }
        PagingResponse<InventoryDTO> response = inventoryService.selectInventoryList(dto);
        model.addAttribute("response", response);
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

        String subject = request.getParameter("subject");
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
        String subject = request.getParameter("subject");
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
