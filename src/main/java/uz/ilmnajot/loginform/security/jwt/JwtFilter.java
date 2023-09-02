package uz.ilmnajot.loginform.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.ilmnajot.loginform.Service.CustomUserDetailsService;
import uz.ilmnajot.loginform.Service.UserService;

import java.io.IOException;
import java.util.ArrayList;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userService;

    public JwtFilter(JwtProvider jwtProvider, CustomUserDetailsService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization!=null && authorization.startsWith("Bearer")) {
            String token = authorization.substring(7);
            String usernameFromToken = jwtProvider.getUsernameFromToken(token);
            if (usernameFromToken != null) {
                UserDetails userDetails = userService.loadUserByUsername(token);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>()));
            }
        }
    filterChain.doFilter(request, response);
    }

}
