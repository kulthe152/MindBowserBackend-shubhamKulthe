package com.sk.orgnisation.SecurtiyConfig.helper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sk.orgnisation.service.ManagerDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ManagerDetailsService managerDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get jwt
		// is started from bearer ?
		//then validate
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
	
		

		
		  System.out.println("WebConfig; "+req.getRequestURI());
		  res.setHeader("Access-Control-Allow-Origin", "*");
		  res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		  res.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
		  res.setHeader("Access-Control-Max-Age", "3600");
		  res.setHeader("Access-Control-Allow-Credentials", "true");
		  res.setHeader("Access-Control-Expose-Headers", "Authorization");
		  res.addHeader("Access-Control-Expose-Headers", "responseType");
		  res.addHeader("Access-Control-Expose-Headers", "observe");
	      System.out.println("Request Method: "+req.getMethod());

		
		String requestTokenHeader = request.getHeader("Authorization");
		String email = null;
		String jwtToken = null;
		System.out.println("requestTokenHeader :: "+requestTokenHeader);
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) //check null and format
		{
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("jwtToken ::"+jwtToken);
			try {				
				email = this.jwtUtil.getUsernameFromToken(jwtToken);				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userDetails = this.managerDetailsService.loadUserByUsername(email);
			
			//security
			if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Token is not validated");
			}
				
			filterChain.doFilter(request, response);
		}
		else
		{
			System.out.println("else JwtAuthenticationFilter exe");
			filterChain.doFilter(request, response);
		}
		
		
		
	}

}
