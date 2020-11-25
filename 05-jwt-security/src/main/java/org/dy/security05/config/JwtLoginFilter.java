package org.dy.security05.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dy.security05.entiy.UserPo;
import org.dy.security05.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caiwl
 * @date 2020/9/7 17:05
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    JwtLoginFilter(String defaultFilterProcessUrl, AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(defaultFilterProcessUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UserPo userPo = new ObjectMapper().readValue(request.getInputStream(), UserPo.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userPo.getUsername(), userPo.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuilder sb = new StringBuilder();
        for (GrantedAuthority authority : authorities) {
            sb.append(authority.getAuthority()).append(",");
        }
        String jwt = JwtUtil.sign(authResult.getName(), sb.toString());
        Map<String, String> data = new HashMap<>();
        data.put("token", jwt);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(data));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("登录失败");
        out.flush();
        out.close();
    }

}
