package dan.turingbank.web.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dan.turingbank.infra.cookieService.ICookieService;
import dan.turingbank.infra.tokenJWT.TokenService;
import dan.turingbank.model.dto.LoginDto;
import dan.turingbank.model.dto.LoginResponseDto;
import dan.turingbank.model.entity.User;
import dan.turingbank.service.interfaces.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/public/auth/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ICookieService cookieService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginDto user, HttpServletResponse response) {

        System.out.println(user.toString());
        if (!userService.usernameAlreadyExists(user.username()))

            throw new RuntimeException("Usuário inexistente ou senha inválida!");

        var usernamePassword = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        Cookie[] cookies = cookieService.generate(token);

        for (Cookie cookie : cookies)
            response.addCookie(cookie);

        return ResponseEntity.ok().body(new LoginResponseDto(token));
    }    
}
