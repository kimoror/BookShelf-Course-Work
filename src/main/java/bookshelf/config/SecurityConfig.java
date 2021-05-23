package bookshelf.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security Configuration
 */
        @EnableWebSecurity
        @Configuration
        @EnableGlobalMethodSecurity(prePostEnabled = true)
        public class SecurityConfig extends WebSecurityConfigurerAdapter {

            private final UserDetailsService userDetailsService;

            public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
                this.userDetailsService = userDetailsService;
            }

    /**
     * Determine configuration of application
     * @param http - HttpSecurity
     * @throws Exception
     */
            protected void configure(HttpSecurity http) throws Exception{
                http
                        .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/auth/registration", "/auth/add").permitAll()
                .antMatchers("/views/**", "/products/**", "/static/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/auth/success")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/auth/login")
        ;
    }


    /**
     *  Set daoAuthenticationProvider to authentification
     * @param auth - authentification
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * @return new BCryptPasswordEncoder with strength 12
     */
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    /**
     * Set passwordEncoder and userDetailsService
     * @return daoAuthenticationProvider with
     */
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
