package com.smartFarm.was.domain.service;

import com.smartFarm.was.domain.model.Token;
import com.smartFarm.was.domain.repository.TokenRepository;
import com.smartFarm.was.web.config.jwt.TokenProvider;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.callback.TraceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenProvider tokenProvider;
    private final TraceTemplate traceTemplate;

    public TokenService(TokenRepository tokenRepository, TokenProvider tokenProvider, LogTrace trace) {
        this.tokenRepository = tokenRepository;
        this.tokenProvider = tokenProvider;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public String saveToken(Long memberId, String memberName, Collection authorities) {
        return traceTemplate.execute("TokenService.saveToken()", () -> {
            String tokenValue = tokenProvider.createToken(memberName, authorities);
            Token token = new Token(memberId, tokenValue);
            tokenRepository.save(token);
            return tokenValue;
        });
    }

    public String findToken() {
        return traceTemplate.execute("TokenService.findToken()", () -> {

        })
    }

    public String destroyToken() {
        return traceTemplate.execute("TokenService.destroyToken()", () -> {

        });
    }
}
