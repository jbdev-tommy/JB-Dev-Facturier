package fr.jbdev.facturier.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import fr.jbdev.domaine.Utilisateurs;
import fr.jbdev.facturier.excepetions.ObjectNullException;
import fr.jbdev.facturier.excepetions.UserIsNotExist;
import fr.jbdev.facturier.service.MyUserDetailService;
import fr.jbdev.facturier.service.UserService;

@Service("myUserDetailsService")
public class MyUserDetailServiceImpl implements UserDetailsService,
	MyUserDetailService {

    @Autowired
    private UserService userService;
    private Utilisateurs user;

    @Override
    public UserDetails loadUserByUsername(final String email) {

	try {
	    user = userService.findUserByMail(email);

	    final List<GrantedAuthority> authorities = buildUserAuthority(user);
	    return buildUserForAuthentication(user, authorities);

	} catch (final UserIsNotExist | ObjectNullException e) {
	    return null;
	}
    }

    private User buildUserForAuthentication(final Utilisateurs user,
	    final List<GrantedAuthority> authorities) {
	new ShaPasswordEncoder(256);

	return new org.springframework.security.core.userdetails.User(
		user.getEmail(), user.getPassWord(), user.isEnabled(), true,
		true, true, authorities);

    }

    private List<GrantedAuthority> buildUserAuthority(final Utilisateurs user)
	    throws UserIsNotExist {
	final Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

	setAuths.add(new SimpleGrantedAuthority(user.getRole()));

	final List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
		setAuths);

	return Result;

    }

    @Override
    public Utilisateurs getUser() {
	return user;
    }

    public void setUser(Utilisateurs user) {
	this.user = user;
    }
}
