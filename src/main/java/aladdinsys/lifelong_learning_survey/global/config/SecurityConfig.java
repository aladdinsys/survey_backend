package aladdinsys.lifelong_learning_survey.global.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import aladdinsys.lifelong_learning_survey.global.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthFilter;

	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll())
			.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		;
		// http
		// 	.authorizeHttpRequests((authorize) -> authorize
		// 		.anyRequest().authenticated()
		// 	)
		// 	.csrf((csrf) -> csrf.ignoringRequestMatchers("/auth/**"))
		// 	.httpBasic(Customizer.withDefaults())
		// 	.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// 	.authenticationProvider(authenticationProvider)
		// 	.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		// ;
		return http.build();
	}
}
