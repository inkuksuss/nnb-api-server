package com.smartFarm.was.domain.model;

import com.smartFarm.was.web.dto.request.JoinDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Getter @Setter
public class Member implements UserDetails {
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", privacyConsent='" + privacyConsent + '\'' +
                ", memberAuthority='" + memberAuthority + '\'' +
                ", memberBirthday=" + memberBirthday +
                ", memberCreated=" + memberCreated +
                ", memberLastUpdated=" + memberLastUpdated +
                ", memberLastAccessed=" + memberLastAccessed +
                '}';
    }

    private Long memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String privacyConsent;
    private String memberAuthority;
    private Timestamp memberBirthday;
    private Timestamp memberCreated;
    private Timestamp memberLastUpdated;
    private Timestamp memberLastAccessed;

    public Member(JoinDto joinDTO, String authority) {
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

    public Member(Optional<Member> member) {
        this.memberId = member.get().getMemberId();
        this.memberName = member.get().getMemberName();
        this.memberPassword = member.get().getMemberPassword();
        this.memberEmail = member.get().getMemberEmail();
        this.memberPhone = member.get().getMemberPhone();
        this.memberAddress = member.get().getMemberAddress();
        this.privacyConsent = member.get().getPrivacyConsent();
        this.memberBirthday = member.get().getMemberBirthday();
        this.memberAuthority = member.get().getMemberAuthority();
        this.memberCreated = member.get().getMemberCreated();
        this.memberLastUpdated = member.get().getMemberLastUpdated();
        this.memberLastAccessed = member.get().getMemberLastAccessed();
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
