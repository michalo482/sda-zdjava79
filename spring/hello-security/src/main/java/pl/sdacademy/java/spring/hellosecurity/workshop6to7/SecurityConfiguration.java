package pl.sdacademy.java.spring.hellosecurity.workshop6to7;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasAnyRole("READER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_READER", "ROLE_ADMIN")
                .anyRequest().denyAll();

        http.httpBasic();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
