package br.com.clarea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.clarea.request.AuthenticationRequest;
import br.com.clarea.service.AuthService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> auth(@RequestBody AuthenticationRequest authenticationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.auth(authenticationRequest));
	}
}
