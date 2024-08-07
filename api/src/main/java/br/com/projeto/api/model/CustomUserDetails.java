package br.com.projeto.api.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {

    private Cliente usuario;

    
    
    public CustomUserDetails(Cliente usuario) {
        super();
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(usuario.getPapel()));
    }

    @Override
    public String getPassword() {
        
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        
        return usuario.getUsername();
    }
    
}
