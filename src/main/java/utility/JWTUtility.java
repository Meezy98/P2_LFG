package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import repository.entities.PublicDetails;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

import java.util.Base64;

import static io.jsonwebtoken.Jwts.parserBuilder;

public class JWTUtility {

    private static final byte[] secret = Base64.getDecoder().decode("V1FrSlZd1CDC7XBh2f+sJaG6OW5gxC0LlTQestx8/wk");

    public static String generateJWT(UserProfile userProfile) {
        return Jwts.builder()
                .claim("firstName", userProfile.getFirstName())
                .claim("lastName", userProfile.getLastName())
                .claim("username", userProfile.getUserID().getUserLogin())
                .claim("userId", userProfile.getUserID().getUserID())
                .claim("account", true)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public static JWTInfo verifyUser(String token) {
        ObjectMapper objectMapper = new ObjectMapper();
        Jws<Claims> jws;
        try{
             jws = Jwts.parserBuilder()
                     .require("account", true)
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(token);
            return objectMapper.convertValue(jws.getBody(), JWTInfo.class);
        }catch(JwtException e){
            e.printStackTrace();
            return null;
        }
    }
}