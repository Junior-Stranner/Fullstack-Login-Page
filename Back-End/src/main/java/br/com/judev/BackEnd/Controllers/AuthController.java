package br.com.judev.BackEnd.Controllers;

import br.com.judev.BackEnd.domain.user.User;
import br.com.judev.BackEnd.dto.LoginRequestDTO;
import br.com.judev.BackEnd.dto.RegisterRequestDTO;
import br.com.judev.BackEnd.dto.ResponseDTO;
import br.com.judev.BackEnd.infra.security.TokenService;
import br.com.judev.BackEnd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        // Busca o usuário no banco de dados pelo email fornecido na requisição
        User user = userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        // Verifica se a senha fornecida na requisição corresponde à senha armazenada no banco de dados para o usuário encontrado
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            // Gera um token JWT para o usuário autenticado
            String token = tokenService.generateToken(user);

            // Retorna uma resposta HTTP 200 (OK) contendo o nome do usuário e o token JWT gerado
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }

        // Se a senha não corresponder, retorna uma resposta HTTP 400 (Bad Request)
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        // Verifica se já existe um usuário com o email fornecido no banco de dados
        Optional<User> user = userRepository.findByEmail(body.email());

        // Se não houver um usuário com o email fornecido, registra um novo usuário
        if (user.isEmpty()) {
            // Cria um novo usuário com os dados fornecidos na requisição
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());

            // Salva o novo usuário no banco de dados
            userRepository.save(newUser);

            // Gera um token JWT para o novo usuário registrado
            String token = this.tokenService.generateToken(newUser);

            // Retorna uma resposta HTTP 200 (OK) contendo o nome do usuário e o token JWT gerado
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }

        // Se já existir um usuário com o email fornecido, retorna uma resposta HTTP 400 (Bad Request)
        return ResponseEntity.badRequest().build();
    }
}