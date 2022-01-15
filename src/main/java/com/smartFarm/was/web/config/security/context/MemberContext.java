package com.smartFarm.was.web.config.security.context;

import com.smartFarm.was.domain.model.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    private final Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberEmail(), member.getMemberPassword(), authorities);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
