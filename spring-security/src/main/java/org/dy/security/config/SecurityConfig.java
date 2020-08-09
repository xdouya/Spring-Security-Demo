package org.dy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author caiwl
 * @date 2020/8/8 23:06
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置如何通过拦截器保护请求
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //任何请求都需要认证
        //vip1请求需要vip1角色
        //vip2请求需要vip2角色
        httpSecurity.authorizeRequests().anyRequest().authenticated()
                .antMatchers("/admin/**").hasAnyRole("admin")
                .antMatchers("/editor/**").hasAnyRole("admin", "editor")
                .and().formLogin().loginPage("/login").failureForwardUrl("/error");
    }

    /**
     * 基于内存的的用户存储认证
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("admin").and()
//                .withUser("user").password("password").roles("editor");
//    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setDataSource(dataSource);
//        return jdbcUserDetailsManager;
//    }
//
//    /**
//     * 基于内存的的用户存储认证
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//        auth.userDetailsService(jdbcUserDetailsManager).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
