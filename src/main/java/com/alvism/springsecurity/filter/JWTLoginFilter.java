package com.alvism.springsecurity.filter;

import com.alvism.springsecurity.security.GrantedAuthorityImpl;
import com.alvism.springsecurity.service.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    /**
     * @param url
     * @param authenticationManager
     */
    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url, "POST"));
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //得到用户登陆信息,并封装到 Authentication 中,供自定义用户组件使用.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();


        ArrayList<GrantedAuthorityImpl> authorities = new ArrayList<>();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);


        return authenticationToken;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {

        TokenAuthenticationService.addAuthenticatiotoHttpHeader(response, authResult);

    }
}
