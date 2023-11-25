package dan.turingbank.infra.cookieService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService implements ICookieService {

    @Value("${expired.authentication}")
    private int expiredAuthentication;

    @Override
    public Cookie[] update(String token) {

        Cookie cookie1 = new Cookie("token-acess", token);
        Cookie cookie2 = new Cookie("isAuth", "true");

        cookie1.setPath("/");
        cookie1.setMaxAge(expiredAuthentication);
        cookie1.setHttpOnly(true);

        cookie2.setMaxAge(expiredAuthentication);
        cookie2.setPath("/");

        return new Cookie[] {cookie1, cookie2};
    }
    @Override
    public Cookie[] generate( String token) {

        Cookie cookie1 = new Cookie("token-acess", token);
        Cookie cookie2 = new Cookie("isAuth", "true");

        cookie1.setPath("/");
        cookie1.setMaxAge(expiredAuthentication);
        cookie1.setHttpOnly(true);

        cookie2.setMaxAge(expiredAuthentication);
        cookie2.setPath("/");


        return new Cookie[] {cookie1, cookie2};
    }
    @Override
    public Cookie[] remove() {

        Cookie cookie1 = new Cookie("token-acess", "");
        Cookie cookie2 = new Cookie("isAuth", "false");
        
        cookie1.setPath("/");
        cookie1.setHttpOnly(true);
        cookie1.setMaxAge(0);
               
        cookie2.setPath("/");
        cookie2.setMaxAge(0);
        
        

    

        return new Cookie[] {cookie1, cookie2};
    }

}
