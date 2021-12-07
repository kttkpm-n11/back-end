package iuh.kttkpm.nhom11.filter;


import iuh.kttkpm.nhom11.entity.User;
import iuh.kttkpm.nhom11.repository.UserRepository;
import iuh.kttkpm.nhom11.ulti.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;
        log.error("token : "+requestTokenHeader);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.replace("Bearer", "").trim();
            log.error("token get: "+jwtToken);
            try {
                username = jwtUtil.getUserFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }

        User user = userRepository.findByUsername(username);
        if (user!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            log.error("true method");
            if (jwtUtil.validateToken(jwtToken,user)){
                UsernamePasswordAuthenticationToken token
                        = new UsernamePasswordAuthenticationToken(username,null,user.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(token);
                log.error("token --- : "+user.getAuthorities().toString());
            }
        }

        filterChain.doFilter(request,response);
    }
}
