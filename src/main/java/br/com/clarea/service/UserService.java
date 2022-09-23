package br.com.clarea.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.clarea.entity.Role;
import br.com.clarea.entity.User;
import br.com.clarea.entity.constants.ERole;
import br.com.clarea.entity.constants.MsgConstants;
import br.com.clarea.exception.AppException;
import br.com.clarea.repository.role.RoleReadRepository;
import br.com.clarea.repository.user.UserReadRepository;
import br.com.clarea.repository.user.UserWriteRepository;
import br.com.clarea.request.CadastroRequest;

@Service
public class UserService {

	@Autowired
	private UserReadRepository userReadRepository;
	
	@Autowired
	private RoleReadRepository roleReadRepository;
	
	@Autowired
	private UserWriteRepository userWriteRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public User cadastrar(CadastroRequest cadastroRequest) {
		if (BooleanUtils.isTrue(userReadRepository.existsByEmail(cadastroRequest.getEmail()))) {
			throw new AppException(MsgConstants.ERROR.EMAIL_EXISTENTE);
		}

		User user = User.builder()
				.email(cadastroRequest.getEmail())
				.active(Boolean.TRUE)
				.name(cadastroRequest.getName())
				.password(encoder.encode(cadastroRequest.getPassword()))
				.build();

		Set<ERole> strRoles = cadastroRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (CollectionUtils.isEmpty(strRoles)) {
			Role userRole = roleReadRepository.findByRole(ERole.ROLE_USER)
					.orElseThrow(() -> new AppException(MsgConstants.ERROR.ROLE_NAO_ENCONTRADA));
			roles.add(userRole);
		} 
		else {
			strRoles.forEach(role -> {
				Role userRole = roleReadRepository.findByRole(role)
						.orElseThrow(() -> new AppException(MsgConstants.ERROR.ROLE_NAO_ENCONTRADA));
				roles.add(userRole);
			});
		}

		user.setRoles(roles);
		return userWriteRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userReadRepository.findAll();
	}

}