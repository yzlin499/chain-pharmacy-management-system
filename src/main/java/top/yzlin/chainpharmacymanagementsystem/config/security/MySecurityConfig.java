package top.yzlin.chainpharmacymanagementsystem.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.yzlin.chainpharmacymanagementsystem.dao.UserDAO;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDAO userDAO;
    private final AuthenticationHandlerImpl authenticationHandler;

    public MySecurityConfig(UserDAO userDAO,
                            AuthenticationHandlerImpl authenticationHandler) {
        this.userDAO = userDAO;
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user**").permitAll()
                .antMatchers("/cpms/**").hasRole("ADMIN")
                .and()
                //登录配置
                .formLogin().loginPage("/login.html").loginProcessingUrl("/user/login")
                .failureHandler(authenticationHandler)
                .successHandler(authenticationHandler)
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                //关闭csrf
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(s -> Optional.ofNullable(userDAO.findUserByUsername(s))
                .orElseThrow(() -> new UsernameNotFoundException("找不到用户名")))
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        return charSequence.toString();
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        return Objects.equals(charSequence.toString(), s);
                    }
                });
    }
}
