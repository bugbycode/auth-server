package com.fort.jidisec.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private ClientDetailsService mongoClientDetailsService;
	
	@Autowired
	private TokenStore mongoTokenStore;
	
	@Autowired
	private UserDetailsService mongoUserDetailsService;
	
	@Autowired
	private AuthorizationCodeServices mongoAuthorizationCodeServices;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(mongoClientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.setClientDetailsService(mongoClientDetailsService);
		endpoints.tokenStore(mongoTokenStore)
				
				.authorizationCodeServices(mongoAuthorizationCodeServices)
				 //.authenticationManager(authenticationManager)
				 .userDetailsService(mongoUserDetailsService).tokenGranter(tokenGranter(endpoints));
		
	}
	
	private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
		
		return new TokenGranter() {
			private CompositeTokenGranter delegate;

			@Override
			public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
				if (delegate == null) {
					delegate = new CompositeTokenGranter(getDefaultTokenGranters(endpoints));
				}
				return delegate.grant(grantType, tokenRequest);
			}
		};
	}
	
	private List<TokenGranter> getDefaultTokenGranters(AuthorizationServerEndpointsConfigurer endpoints) {
		ClientDetailsService clientDetails = endpoints.getClientDetailsService();
		AuthorizationServerTokenServices tokenServices = endpoints.getTokenServices();
		AuthorizationCodeServices authorizationCodeServices = endpoints.getAuthorizationCodeServices();
		OAuth2RequestFactory requestFactory = endpoints.getOAuth2RequestFactory();

		List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
		tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails,
				requestFactory));
		tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
		ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory);
		tokenGranters.add(implicit);
		tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
		if (authenticationManager != null) {
			tokenGranters.add(new FortResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,
					clientDetails, requestFactory));
		}
		return tokenGranters;
	}
}
