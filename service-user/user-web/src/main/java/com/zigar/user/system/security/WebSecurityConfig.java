//package com.zigar.user.system.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//
///**
// * WebSecurityConfigurerAdapter配置一系列的filter形成springFilterChain。
// * Builder构造类，Configure配置类
// * Builder调用build方法再调用Configure的init方法进行具体的配置，performBuild方法生成具体的执行类或者过滤器
// */
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private CustomUserDetailService customUserDetailService;
//
////    @Autowired
////    private DemoFilter demoFilter;
//
////    @Autowired
////    private ImageCodeAuthenticationFilter imageCodeAuthenticationFilter;
//
////    @Autowired
////    private CustomLoginFailHandler customLoginFailHandler;
////
////    @Autowired
////    private CustomLoginSuccessHandler customLoginSuccessHandler;
//
////    @Autowired
////    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
////
////    @Autowired
////    private SecurityProperties securityProperties;
//
////    @Bean
////    PasswordEncoder passwordEncoder() {
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
////    }
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManager() throws Exception {
////        return super.authenticationManager();
////    }
//
//    /**
//     * 重写父类的configure方法，父类的默认方法不再执行，只有子类调用super才会调用
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
////                .authorizeRequests()  //定义哪些url需要保护，哪些url不需要保护
////                .antMatchers(securityProperties.getPermitAllUrls())
////                .permitAll()    //定义不需要认证就可以访问
////                .anyRequest()
////                .fullyAuthenticated()
////                .authenticated()
//
////                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
////                .and()
////                .logout()
////                .logoutSuccessHandler(customLogoutSuccessHandler);
//
////        LogoutWebFilter
////        LogoutFilter
//
////        http.addFilterAt(imageCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////        http.addFilterBefore(demoFilter, AnonymousAuthenticationFilter.class);
//
//    }
//
//    /**
//     * 重写父类的configure(AuthenticationManagerBuilder)方法，使父类不使用默认的验证模式，改用自定义验证
//     */
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
////    }
//
//}
