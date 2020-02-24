package com.app.ssoserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter{
	
	private static final String CLIEN_ID = "SampleClientId";
	private static final String CLIENT_SECRET ="secret";
	private static final String TYPE_PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String USER_INFO = "user_info";
	private static final int VALIDITY_TOKEN = 3600;
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("as466gf");
		return converter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(CLIEN_ID)
			.secret(passwordEncoder.encode(CLIENT_SECRET))
			.authorizedGrantTypes(TYPE_PASSWORD,AUTHORIZATION_CODE,REFRESH_TOKEN,IMPLICIT)
			.scopes(USER_INFO)
			.autoApprove(true)
			.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
			.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
			.redirectUris("http://localhost:8082/ui/login","http://localhost:8083/ui2/login","http://localhost:8082/login")
			.accessTokenValiditySeconds(VALIDITY_TOKEN);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
		.authenticationManager(authenticationManager)
		.accessTokenConverter(accessTokenConverter());
	}
}
