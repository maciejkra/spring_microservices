package pl.jsystems.micro.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()     // poniższe adresy wymagają autoryzacji
            .antMatchers("/users/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
            .anyRequest().permitAll()        // pozostałe żądania nie wymagają autoryzacji
            .and()
                .formLogin()
            .and()
                .httpBasic();
    }
}
