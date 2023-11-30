package dan.turingbank.web.apiControllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.turingbank.infra.cookieService.ICookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth/logout")
public class LogoutController {

    @Autowired
    private ICookieService cookieService;

    @GetMapping
    public ResponseEntity<?> logout(HttpServletResponse response) throws IOException {

        Cookie[] cookies = cookieService.remove();

        for (Cookie cookie : cookies)
            response.addCookie(cookie);

        System.out.println("logout");

        return ResponseEntity.ok().build();
    }
}
