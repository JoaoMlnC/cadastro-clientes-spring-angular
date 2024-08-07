package br.com.projeto.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import br.com.projeto.api.model.Cliente;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public String test() {
        return "Endpoint de teste está funcionando!";
    }
    @PostMapping("/receiveCliente")
    public String receiveCliente(@RequestBody Cliente cliente) {
        System.out.println("Cliente recebido: " + cliente);
        return "Cliente recebido!";
    }

    

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente cliente) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(cliente.getUsername(), cliente.getPassword())
            );
            return ResponseEntity.ok().body("{\"message\": \"Login bem-sucedido\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Falha no login: " + e.getMessage() + "\"}");
        }
    }

}