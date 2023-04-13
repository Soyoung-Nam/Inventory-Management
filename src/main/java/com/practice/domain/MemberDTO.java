package com.practice.domain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Getter
@Setter
public class MemberDTO {

    /* 회원번호 */
    private int no;

    /* 아이디 */
    @NotBlank(message = "아이디를 입력해주세요.") //유효성 검사를 실행할 변수
    private String id;

    /* 이름 */
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    /* 비밀번호 */
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 숫자, 영문 대소문자, 특수문자를 사용하세요.")
    private String pw;

    /* 이메일 */
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    /* 생년월일 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDate;

    /* 회원가입년도 */
    private Date joinDate;

    /* 핸드폰번호 */
    @NotBlank(message = "핸드폰번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9]).{0,11}", message = "휴대번호는 숫자만 입력하세요.")
    private String phone;

    /* 권한 */
    private String role;

    /* getter setter - lombok */
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /* console에 DTO값 전체출력하기 */

    @Override
    public String toString() {
        return "MemberDTO{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", joinDate=" + joinDate +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
