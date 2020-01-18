package top.yzlin.chainpharmacymanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
//        http.authorizeRequests().antMatchers("/").permitAll();
//                .antMatchers("/level1/**").hasRole("VIP1")
//                .antMatchers("/level2/**").hasRole("VIP2")
//                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().loginPage("/login.html");
        //1、 /login 来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细功能
        //4、默认post形式的 /login 代表处理登陆
        //5、一旦定制loginPage  那么 loginPage的post请求就是登陆


        //开启自动配置的注销功能
//        http.logout().logoutSuccessUrl("/"); //注销成功以后来到首页
        //1、访问/logout 表示用户注销。清空 session
        //2、注销成功会返回 /login?logout 页面
        //3、默认post形式的 /login代表处理登陆


        //开启记住我功能
//        http.rememberMe().rememberMeParameter("remeber");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("q1w2e3r4").roles("ADMIN");
    }
}
