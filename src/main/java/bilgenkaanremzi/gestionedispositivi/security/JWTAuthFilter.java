package bilgenkaanremzi.gestionedispositivi.security;

import bilgenkaanremzi.gestionedispositivi.entities.User;
import bilgenkaanremzi.gestionedispositivi.exceptions.UnautorizedException;
import bilgenkaanremzi.gestionedispositivi.services.UserService;
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

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(("Authorization"));
        if (authHeader==null || !authHeader.startsWith("Bearer ")) {
            throw new UnautorizedException("Please pass the Bearer Token");
        } else {
            String token = authHeader.substring(7);
            System.out.println("TOKEN -> " + token);
            jwtTools.verifyToken(token);

            String id = jwtTools.extractIdFromToken(token);
            User currentUser = userService.findById(Integer.parseInt(id));


            Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthoroties());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request,response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)throws ServletException{
        return new AntPathMatcher().match("/auth/**",request.getServletPath());
    }
}
