package br.com.clarea.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.clarea.configuration.service.UserDetailsImpl;
import br.com.clarea.configuration.util.JwtUtil;
import br.com.clarea.entity.constants.MsgConstants;
import br.com.clarea.exception.AppException;
import br.com.clarea.request.AuthenticationRequest;
import br.com.clarea.response.AuthenticationResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse auth(AuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtUtil.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			List<String> roles = userDetails.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.toList());

			return AuthenticationResponse.builder()
					.token(token)
					.id(userDetails.getId())
					.email(userDetails.getEmail())
					.name(userDetails.getUsername())
					.roles(roles)
					.build();
		} catch (Exception e) {
			log.error("ERRO AO AUTENTICAR: " + e.getMessage());
			throw new AppException(MsgConstants.ERROR.AUTENTICACAO);
		}
	}

}
