package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.smartcontactmanager.smartContactManagerServer.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtServiceImpl implements JwtService {
  private final String secretKey1="m4privatat35trigf0r5ignaturehbihdffuakfhdjoiaijfdofj";


  // Generatng secretKey with keyGenerator

  // private  String signatureKey="";
  // public JwtServiceImpl(){
  //   try {
  //     KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
  //     SecretKey sk=keyGenerator.generateKey();
  //     signatureKey=Base64.getEncoder().encodeToString(sk.getEncoded());
  //     System.out.println("SignatureKey: "+signatureKey);
  //   } catch (NoSuchAlgorithmException e) {
  //         throw new RuntimeException(e);
  //   }
  // }


    @Override
    public String generateToken(String username) {
      Map<String,Object> claims=new HashMap<>();

      return Jwts.builder()
                  .claims()
                  .add(claims)
                  .subject(username)
                  .issuedAt(new Date(System.currentTimeMillis()))
                  .expiration(new Date(System.currentTimeMillis()+60*60*30))
                  .and()
                  .signWith(getKey())
                  .compact();

    }

    private SecretKey getKey() {
      byte[] keyBytes=Decoders.BASE64.decode(secretKey1);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUserName(String token) {
      return extractClaim(token,Claims::getSubject);
    }

    private <T> T extractClaim(String token,Function<Claims,T> claimResolver) {
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
      return Jwts.parser()
                 .verifyWith(getKey())
                 .build()
                 .parseSignedClaims(token)
                 .getPayload();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
      final String username=extractUserName(token);
      return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
     return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
     return extractClaim(token,Claims::getExpiration);
    }
    
}
