package dan.turingbank.infra.tokenJWT;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import dan.turingbank.model.entity.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

    String secrety = "abcde";

    @Value("${expired.authentication}")
    private int expiredAuthentication;

    public String generateToken(User user) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secrety);

            String token = JWT.create().withIssuer("banco-digital-descomplicado")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusSeconds(expiredAuthentication).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            return token;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar token");
        }
    }

    public String validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(secrety);

            return JWT.require(algorithm)
                    .withIssuer("banco-digital-descomplicado")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
           return null;
        }

    }
}
