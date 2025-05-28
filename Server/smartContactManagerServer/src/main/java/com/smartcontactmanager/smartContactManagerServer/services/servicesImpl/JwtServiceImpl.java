package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.smartcontactmanager.smartContactManagerServer.services.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtServiceImpl implements JwtService {
  // private final String secretKey="m4privatat35trigf0r5ignaturehbihdffuakfhdjoiaijfdofjduajdnouaekiasgfdvdgaydhvhviuhivuhvihduhavuhuhvhfgsdufivhfuhifuhvuidhsiu";
  private  String signatureKey="";
  public JwtServiceImpl(){
    try {
      KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk=keyGenerator.generateKey();
      signatureKey=Base64.getEncoder().encodeToString(sk.getEncoded());
    } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
    }
  }

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

    private Key getKey() {
      byte[] keyBytes=Decoders.BASE64.decode(signatureKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
