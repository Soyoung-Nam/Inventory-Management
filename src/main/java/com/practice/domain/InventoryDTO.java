package com.practice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
public class InventoryDTO {

    /* 회원번호 */
    private int no;

    /* 품목번호 */
    private int subjectNo;

    /* 품목 */
    private int subject;

    /* 구매자 */
    private String buyer;

    /* 구매날짜 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String buyDt;

    /* 가격 */
    private int amount;

    /* 작성날짜 */
    private Date createDt;

    /* 수정날짜 */
    private Date updateDt;

    /* 삭제로그 */
    private int delFlag;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyDt() {
        return buyDt;
    }

    public void setBuyDt(String buyDt) {
        this.buyDt = buyDt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "no=" + no +
                ", subjectNo=" + subjectNo +
                ", subject=" + subject +
                ", buyer='" + buyer + '\'' +
                ", buyDt='" + buyDt + '\'' +
                ", amount=" + amount +
                ", createDt=" + createDt +
                ", updateDt=" + updateDt +
                ", delFlag=" + delFlag +
                '}';
    }
}
