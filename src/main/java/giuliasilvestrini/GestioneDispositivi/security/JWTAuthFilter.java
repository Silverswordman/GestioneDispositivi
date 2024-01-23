package giuliasilvestrini.GestioneDispositivi.security;

import giuliasilvestrini.GestioneDispositivi.entities.User;
import giuliasilvestrini.GestioneDispositivi.exceptions.UnathorizedException;
import giuliasilvestrini.GestioneDispositivi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    // Creo un filtro che andrò ad aggiungere alla Security Filter Chain

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnathorizedException("Per favore metti il token nell'Authorization header");
        } else {
            String accessToken = authHeader.substring(7);


            jwtTools.verifyToken(accessToken);

            String id = jwtTools.extractIdFromToken(accessToken);
            User user = userService.findById(UUID.fromString(id));

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            filterChain.doFilter(request, response);
        }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {


        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }


}