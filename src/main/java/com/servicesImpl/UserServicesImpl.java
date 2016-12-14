package com.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.UserDao;
import com.entities.Client;
import com.entities.Employee;
import com.entities.User;
import com.services.UserServices;

@Service
public class UserServicesImpl extends GenericServiceImpl<User, Integer> implements UserServices, UserDetailsService{

	private UserDao userDao;
	
	public UserServicesImpl(){
		
	}
	
    @Autowired
    public UserServicesImpl(
            @Qualifier("userDaoImpl") GenericDao<User, Integer> genericDao) {
        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }

	public User validateLogin(String username, String password) {
		try{
		User user=userDao.getUserByName(username);
		if(user.getPassword().equals(password)) return user;
		return null;
		}catch(NullPointerException e){
			return null;
		}
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return userDao.getAllEmployees();
	}

	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return userDao.getAllClients();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,DataAccessException {
        User person = userDao.getUserByName(username);
            if(person == null) { throw new UsernameNotFoundException("Wrong username or password");} 
        
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(person.getEmail(),
        																				 person.getPassword(), 
        																				 person.isActive(), 
        																				 true, 
        																				 true, 
        																				 true, 
        																				 getAuthorities(person));
        return userDetails;
    }
	
	private Set<GrantedAuthority> getAuthorities(User user){  
		String rol = user.getRol();
        String userRol = "ROLE_ADMIN";
        if(rol.equals("Client")){
        	userRol = "ROLE_USER";
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRol);  
        authorities.add(grantedAuthority);  
        return authorities;  
    }
}
