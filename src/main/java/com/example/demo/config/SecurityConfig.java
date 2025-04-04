package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/resources/**").permitAll() // ログインページやリソースを許可
                .anyRequest().authenticated() // 他のリクエストは認証が必要
            )
            .formLogin((form) -> form
                .loginPage("/login") // カスタムログインページ
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .permitAll()
            );
        return http.build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
            .username("aaa") // あなたの好きなユーザー名を設定
            .password(passwordEncoder().encode("aaa")) // あなたのパスワードを設定
            .roles("USER") // ここは役割を統一
            .build();

        return new InMemoryUserDetailsManager(user);
    }

}
