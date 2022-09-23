package br.com.clarea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.clarea.repository.user.UserViewRepository;
import br.com.clarea.request.CadastroRequest;
import br.com.clarea.service.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserViewRepository userViewRepository;

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrar(@RequestBody CadastroRequest cadastroRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.cadastrar(cadastroRequest));
	}

	// @PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping(value = "/v2/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUsersView(@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size) {
		return ResponseEntity.ok(userViewRepository.findAll(PageRequest.of(page, size)));
	}

}