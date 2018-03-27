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



    /*1.首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。
      2.我们在这里注册CustomUserService的Bean，然后通过重写configure方法添加我们自定义的认证方式。
      3.在configure(HttpSecurity http)方法中，我们设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，
      也设置了注销请求，注销请求也是任何人都可以访问的。
      4.permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
      5.这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，
      我可以这样来写：.antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也可以设置admin文件夹下的文件可以有多个角色来访问，
      写法如下：.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
      6.可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的资源，
      写法如下.antMatchers("/admin/**").hasIpAddress("210.210.210.210")*/
    /*7.更多权限
      方法名                              用途
      access(String)                    Spring EL表达式结果为true时可访问
      anonymous()                       匿名可访问
      denyAll()                         用户不可以访问
      fullyAuthenticated()              用户完全认证可访问（非remember me下自动登录）
      hasAnyAuthority(String...)        参数中任意权限的用户可访问
      hasAnyRole(String...)             参数中任意角色的用户可访问
      hasAuthority(String)              某一权限的用户可访问
      hasRole(String)                   某一角色的用户可访问
      permitAll()                       所有用户可访问
      rememberMe()                      允许通过remember me登录的用户访问
      authenticated()                   用户登录后可访问
      haslpAddress(String)              用户来自参数中的ip可访问*/
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
