package com.smartFarm.was.domain.model;

import com.smartFarm.was.dto.request.JoinDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Getter @Setter
public class Member implements UserDetails {
    @Override
    public String
    toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", privacyConsent='" + privacyConsent + '\'' +
                ", memberBirthday=" + memberBirthday +
                ", memberCreated=" + memberCreated +
                ", memberLastUpdated=" + memberLastUpdated +
                ", memberLastAccessed=" + memberLastAccessed +
                ", memberAuthority='" + memberAuthority + '\'' +
                '}';
    }

    private Long memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String privacyConsent;
    private Timestamp memberBirthday;
    private Timestamp memberCreated;
    private Timestamp memberLastUpdated;
    private Timestamp memberLastAccessed;
    private String memberAuthority;

    public Member(JoinDTO joinDTO, String authority) {
        this.memberName = joinDTO.getMemberName();
        this.memberPassword = joinDTO.getMemberPassword();
        this.memberEmail = joinDTO.getMemberEmail();
        this.memberPhone = joinDTO.getMemberPhone();
        this.memberAddress = joinDTO.getMemberAddress();
        this.privacyConsent = joinDTO.getPrivacyConsent();
        this.memberBirthday = joinDTO.getMemberBirthday();
        this.memberAuthority = authority;
        this.memberCreated = new Timestamp(System.currentTimeMillis());
        this.memberLastUpdated = new Timestamp(System.currentTimeMillis());
        this.memberLastAccessed = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(memberAuthority));
        return auth;
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberEmail;
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

// "memberPhone": "01026727162",
// "memberAddress": "상동",
// "memberBirthday": "2020-10-11T01:01:01Z",
// "memberAuthority": ["USER"],
// "privacyConsent": "true",
// "memberPassword": "123"
