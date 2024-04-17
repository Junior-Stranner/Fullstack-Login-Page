package br.com.judev.BackEnd.infra.security;

import br.com.judev.BackEnd.domain.user.User;
import br.com.judev.BackEnd.repository.UserRepository;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
     try{
         Algorithm algorithm = Algorithm.HMAC256("");
     }catch (JWTCreationException exception){
        throw new RuntimeException("Error at authentication");
     }
    }
}
