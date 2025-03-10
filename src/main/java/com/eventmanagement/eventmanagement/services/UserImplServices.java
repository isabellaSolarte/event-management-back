package com.eventmanagement.eventmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventmanagement.eventmanagement.AccesoDatos.models.User;
import com.eventmanagement.eventmanagement.AccesoDatos.repositories.UserRepository;

@Service
public class UserImplServices implements IUserServices{

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return (List<User>) usuarioRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usuarioRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    @Override
    public User update(User user, int id) {
        return usuarioRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            
            // Si se proporciona una nueva contraseña, encriptarla antes de actualizar
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            return usuarioRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public User findByName(String name) {
        return usuarioRepository.findByName(name).get();
    }
    
}
