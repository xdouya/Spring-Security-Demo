package org.dy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author caiwl
 * @date 2020/8/9 11:46
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置拦截器保护请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("admin")
                .antMatchers("/vip/**").hasAnyRole("vip")
                .antMatchers("/user/**").hasAnyRole("user")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
    }

    /**
     * UserDetailsService 用户名，密码，以及其他属性的查找，Spring Security提供内存以及JDBC实现
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDetailsManager.createUser(User.builder().username("admin").password("{bcrypt}" + bCryptPasswordEncoder.encode("088114")).
                roles("admin").build());
        userDetailsManager.createUser(User.builder().username("vip").password("{bcrypt}" + bCryptPasswordEncoder.encode("088114"))
                .roles("vip").build());
        userDetailsManager.createUser(User.builder().username("user").password("{bcrypt}" + bCryptPasswordEncoder.encode("088114"))
                .roles("user").build());
        return userDetailsManager;
    }

    /**
     * 根据自动匹配密码编码器
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
