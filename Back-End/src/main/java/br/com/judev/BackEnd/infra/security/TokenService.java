package br.com.judev.BackEnd.infra.security;

import br.com.judev.BackEnd.domain.user.User;
import br.com.judev.BackEnd.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Injeção de dependência do segredo (chave) usada para assinar e verificar o token
    @Value("${api.security.token.secret}")
    private String secret;

    // Método para gerar um token JWT com base no usuário fornecido
    public String generateToken(User user) {
        try {
            // Criação do algoritmo de assinatura usando HMAC com SHA-256 e a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Geração do token JWT com algumas reivindicações (claims) básicas
            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm); // Assinatura do token com o algoritmo criado
            return token;
        } catch (JWTCreationException exception) {
            // Tratamento de exceção caso ocorra um erro na criação do token
            throw new RuntimeException("Error at authentication");
        }
    }
    // Método para validar um token JWT fornecido
    public String validateToken(String token) {
        try {
            // Criação do algoritmo de verificação usando HMAC com SHA-256 e a chave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Verificação e decodificação do token JWT fornecido
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject(); // Retorna o assunto (subject) do token (nesse caso, o email do usuário)
        } catch (JWTVerificationException exception) {
            // Tratamento de exceção caso ocorra um erro na verificação do token
            return null; // Retorna null se o token não puder ser verificado com sucesso
        }
    }
    // Método para gerar a data de expiração do token (2 horas a partir do momento atual)
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));
    }
}