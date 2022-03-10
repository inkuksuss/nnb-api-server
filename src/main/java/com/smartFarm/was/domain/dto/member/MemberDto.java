package com.smartFarm.was.domain.dto.member;

import com.smartFarm.was.domain.entity.Member;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@ToString
public class MemberDto implements UserDetails {

    private final Member member;
    private final Collection<? extends GrantedAuthority> authorities;

    public MemberDto(Member member, Collection<? extends GrantedAuthority> authorities) {
        this.member = member;
        this.authorities = authorities;
    }
    public Member getMember() {
        return member;
    }

    public long getMemberId() { return member.getMemberId(); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
