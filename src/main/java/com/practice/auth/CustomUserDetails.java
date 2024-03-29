package com.practice.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.sql.Date;
import java.util.Collection;

///// 스프링 시큐리티에서 사용자의 정보를 담는 인터페이스 /////
//form의 Username(Id)와 Password 정보가 담긴 Authentication 객체를 생성하여 AuthenticationManager에 넘긴다.
//Spring Security > Authentication 객체 > UserDetails 객체
@Data //@Getter, @Setter, @ToString, @RequiredArgsConstructor, @EqualsAndHashCode 어노테이션을 한꺼번에 설정해주는 어노테이션
public class CustomUserDetails implements UserDetails {

    /* 회원번호 */
    private int no;
    /* 아이디 */
    private String id;
    /* 이름 */
    private String name;
    /* 비밀번호 */
    private String pw;
    /* 이메일 */
    private String email;
    /* 생년월일 */
    private String birthDate;
    /* 회원가입년도 */
    private Date joinDate;
    /* 핸드폰번호 */
    private String phone;
    /* 권한 */
    private String role;

    /* 권한 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /* 비밀번호 */
    @Override
    public String getPassword() {
        return this.getPw();
    }

    /* 아이디 */
    @Override
    public String getUsername() {
        return this.getId();
    }

    /* 계정만료여부
    * true : 만료안됨
    * false : 만료됨
    */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* 계정잠김여부
     * true : 잠기지않음
     * false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /* 비밀번호만료여부
     * true : 만료안됨
     * false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* 사용자활성화여부
     * true : 활성화
     * false : 비활성화
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
