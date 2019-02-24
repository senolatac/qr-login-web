package sha.com.qrlogin.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import sha.com.qrlogin.handler.MyUrlAuthenticationSuccessHandler;
import sha.com.qrlogin.utils.ConfigUtil;
import sha.com.qrlogin.utils.PageUtil;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
    DataSource dataSource;
	
    @Autowired
    PersistentTokenRepository tokenRepository;
    
    @Autowired
    MyUrlAuthenticationSuccessHandler customSuccessHandler;
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.csrf().disable()
        .authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/views/public/**").permitAll()
        .antMatchers("/views/user/**").access("hasRole('USER')")
        .antMatchers("/views/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/rest/**").permitAll()
        .antMatchers("/app/**","/chat/**","/queue/**","/topic/**").permitAll()
        .antMatchers("/rest/userm/**","/rest/user/list/**").access("hasRole('ADMIN')")
        .antMatchers("/rest/user/update/**","/rest/user/password/**").access("hasAnyRole('USER','ADMIN')")
        .antMatchers("/image/**").permitAll()
        .antMatchers(PageUtil.HOME_PAGE).access("hasAnyRole('USER','ADMIN')")
        .and().httpBasic().realmName(ConfigUtil.REALM_NAME).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().formLogin().loginPage("/login").successHandler(customSuccessHandler).failureUrl("/login?error").loginProcessingUrl("/rest/user/authenticate/").usernameParameter("ssoId").passwordParameter("password")
        .and().logout().logoutSuccessUrl("/login?logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
        .and().rememberMe().rememberMeParameter("rememberMe")
    	.tokenRepository(tokenRepository).tokenValiditySeconds(86400).and().exceptionHandling().accessDeniedPage("/views/error/403.jsp");
    }
    
    @Bean   
    public AbstractRememberMeServices rememberMeServices() {

        PersistentTokenBasedRememberMeServices rememberMeServices =
            new PersistentTokenBasedRememberMeServices("AppKey",userDetailsService,persistentTokenRepository());
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
    
    
    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "rememberMe", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }
    
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
    
    @Bean
    @Order( -10001)
    public MultipartFilter multipartFilter() {
    	MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
