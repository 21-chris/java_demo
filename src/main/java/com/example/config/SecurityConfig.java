package com.example.config;//package com.example.config;
//
//import com.example.bo.AdminUserDetails;
//import com.example.mapper.UserMapper;
//import com.example.pojo.User;
//import com.macro.mall.model.UmsAdminExample;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.List;
//
///**
// * SpringSecurity相关配置
// * Created by macro on 2018/4/26.
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()//配置权限
////                .antMatchers("/").access("hasRole('TEST')")//该路径需要TEST角色
////                .antMatchers("/brand/list").hasAuthority("TEST")//该路径需要TEST权限
//                .antMatchers("/**").permitAll()
//                .and()//启用基于http的认证
//                .httpBasic()
//                .realmName("/")
//                .and()//配置登录页面
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=true")
//                .and()//配置退出路径
//                .logout()
//                .logoutSuccessUrl("/")
////                .and()//记住密码功能
////                .rememberMe()
////                .tokenValiditySeconds(60*60*24)
////                .key("rememberMeKey")
//                .and()//关闭跨域伪造
//                .csrf()
//                .disable()
//                .headers()//去除X-Frame-Options
//                .frameOptions()
//                .disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //获取登录用户信息
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                UmsAdminExample example = new UmsAdminExample();
//                example.createCriteria().andUsernameEqualTo(username);
//                List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
//                if (umsAdminList != null && umsAdminList.size() > 0) {
//                    return new AdminUserDetails(umsAdminList.get(0));
//                }
//                throw new UsernameNotFoundException("用户名或密码错误");
//            }
//        };
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //关闭csrf
        .csrf().disable()
        //不通过Session获取SecurityContext
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        // 对于登录接口 允许匿名访问
        .antMatchers("/user/login").anonymous()
        // 除上面外的所有请求全部需要鉴权认证
        .anyRequest().authenticated();
        //允许跨域
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        //配置异常处理器
        ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer = http.exceptionHandling()
//                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        //允许跨域
        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
