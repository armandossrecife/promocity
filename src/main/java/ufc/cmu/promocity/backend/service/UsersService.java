package ufc.cmu.promocity.backend.service;

import ufc.cmu.promocity.backend.repository.*;
import ufc.cmu.promocity.backend.model.*;
import ufc.cmu.promocity.backend.service.exceptions.UserNotFoundException;
import ufc.cmu.promocity.backend.service.interfaces.IUserService;
import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para consumir o repositório de dados de Usuário
 * @author armandosoaressousa
 *
 */
@Service
public class UsersService extends AbstractService<Users, Long>{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	protected JpaRepository<Users, Long> getRepository(){
		return usersRepository;
	}
	
	public Users getUserByUserName(String username) {
		return usersRepository.findByUsername(username);
	}
	
	public Users getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
}