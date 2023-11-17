package dan.turingbank.infra.cookieService;



import jakarta.servlet.http.Cookie;


public interface ICookieService {

    public Cookie[] update( String token);

    public Cookie[] generate(String token);

    public Cookie[] remove();
}
