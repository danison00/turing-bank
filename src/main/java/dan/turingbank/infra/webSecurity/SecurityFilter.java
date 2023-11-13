package dan.turingbank.infra.webSecurity;

import java.io.IOException;

import dan.turingbank.infra.tokenJWT.TokenService;
import dan.turingbank.model.entity.User;
import dan.turingbank.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, RuntimeException {

        String token = this.recoverToken(request);

        if (token != null) {

            String username = tokenService.validateToken(token);

            if (username != null) {

                UserDetails user = userService.loadUserByUsername(username);
                var userAuthentication = new UsernamePasswordAuthenticationToken(username, user, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                this.updateToken(request, response, user);
            }
        }

        filterChain.doFilter(request, response);

    }

    protected String recoverToken(HttpServletRequest request) {

        Cookie cookie = findCookie(request.getCookies());

        if (cookie != null)
            return cookie.getValue();

        // var authHeader = request.getHeader("Authorization");

        // if(authHeader == null) return null;

        // return authHeader.replace("Bearer ", "");
        return null;
    }

    protected void updateToken(HttpServletRequest request, HttpServletResponse response, UserDetails user) {

        String token = tokenService.generateToken((User) user);
        // Cookie cookie = findCookie(request.getCookies());
        // if (cookie != null) {
        Cookie cookie = new Cookie("token-acess", token);
        cookie.setPath("/");
        cookie.setMaxAge(3*60);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        // }

    }

    protected Cookie findCookie(Cookie[] cookies) {
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("token-acess")) {

                    return cookie;
                }

        return null;
    }
}
