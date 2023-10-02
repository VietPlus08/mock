package fa.com.mock_back_end.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import fa.com.mock_back_end.service.impl.JwtServiceImpl;
import fa.com.mock_back_end.service.impl.UserInforServiceImpl;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Autowired
    private UserInforServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	Cookie cookie = WebUtils.getCookie(request, "jwtToken");
	String token = null;
	String username = null;
	if (cookie != null) {
	    token = cookie.getValue();
	    username = jwtServiceImpl.extractUsername(token);
	}

	if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	    UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
	    if (jwtServiceImpl.validateToken(token, userDetails)) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
			null, userDetails.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	    }
	}
	filterChain.doFilter(request, response);
    }

}
