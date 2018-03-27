package com.cxd.springsecurity.config;

import com.cxd.springsecurity.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created with IntelliJ IDEA.
 * User: cxd
 * Date: 2017/11/25
 * Description:
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /*http.authorizeRequests()
               .anyRequest().authenticated()
               .and()
               .formLogin()
                    .loginPage("/login")
               //设置默认登录成功跳转页面
               .defaultSuccessUrl("/home").failureUrl("/login?error").permitAll()
               .and()
                //开启cookie保存用户数据
               .rememberMe()
               //设置cookie有效期
               .tokenValiditySeconds(60*60*24*7)
               //设置cookie的私钥
               .key("")
               .and()
               .logout()
               //默认注销行为为logout，可以通过下面的方式来修改
               .logoutUrl("/custom-logout")
               //设置注销成功或跳转的页面,默认是跳转到登录页面
               .logoutSuccessUrl("")
               .permitAll();*/
        http.authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
