package com.bookstore.customer.auth;

import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.utils.MessageSupplier;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenValidation  {

	static Logger logger = LoggerFactory.getLogger(TokenValidation.class);
	
	private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	@Autowired
	private MessageSupplier messageSource;
	
    public ValidationResponse handleRequest(String authorizationHeader) {
    	try {
			String bearer = authorizationHeader.substring(0,7).trim();
			String checkBearer = bearer.equals("Bearer ") ? bearer : "Bearer";
			if(!StringUtils.equalsAnyIgnoreCase(checkBearer, "Bearer")) {
				return ValidationResponse.builder().failureMessage(messageSource.get(MessageConstant.AuthConstants.MISSING_BEARER)).tokenValidated(false).build();
			}
			String authToken = bearer.equals("Bearer") ? authorizationHeader.substring(7).trim() : authorizationHeader;
            String	loginKey = getLoginKeyFromToken(authToken);
            if (StringUtils.isNotBlank(loginKey)) {
            	if (!isTokenExpired(authToken)) {
            		TokenData principle = getPrincipleFromClaims(authToken);
            		principle.setToken(authorizationHeader);
            		return ValidationResponse.builder().principle(principle).tokenValidated(true).build();

            	}else {
            		return ValidationResponse.builder().failureMessage(messageSource.get(MessageConstant.AuthConstants.TOKEN_EXPIRED)).tokenValidated(false).build();
            	}
            }else {
              	return ValidationResponse.builder().failureMessage(messageSource.get(MessageConstant.AuthConstants.INVALID_LOGIN_KEY)).tokenValidated(false).build();
                
            }
    	}catch (ExpiredJwtException e) {
//    		e.printStackTrace();
    		return ValidationResponse.builder().failureMessage(messageSource.get(MessageConstant.AuthConstants.TOKEN_EXPIRED)).tokenValidated(false).build();
    	}catch(Exception ex ) {
			return ValidationResponse.builder().failureMessage(ex.getMessage()).tokenValidated(false).build();
    	}
    }
  
  //retrieve username from jwt token
  	public String getLoginKeyFromToken(String token) {
  		return getClaimFromToken(token, Claims::getSubject);
  	}
    
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
    
  //for retrieveing any information from token we will need the secret key
  	private Claims getAllClaimsFromToken(String token) {
  		return Jwts 
                .parserBuilder() 
                .setSigningKey(getSignKey()) 
                .build() 
                .parseClaimsJws(token) 
                .getBody(); 
  	}
  	
  	
  //validate token
  	public Boolean validateToken(String token, String userKey) {
  		final String userKeyFromToken = getLoginKeyFromToken(token);
  		return (userKeyFromToken.equals(userKey) && !isTokenExpired(token));
  	}
  	
  //check if the token has expired
  	private Boolean isTokenExpired(String token) {
  		final Date expiration = getExpirationDateFromToken(token);
  		return expiration.before(new Date());
  	}
  	
  //retrieve expiration date from jwt token
  	public Date getExpirationDateFromToken(String token) {
  		return getClaimFromToken(token, Claims::getExpiration);
  	}
  	
  	private TokenData getPrincipleFromClaims(String token) {
  		final Claims claims = getAllClaimsFromToken(token);
  		ObjectMapper mapper = new ObjectMapper();
  		return mapper.convertValue(claims.get("user", LinkedHashMap.class), TokenData.class) ;
  	}
  	
  	protected static Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
