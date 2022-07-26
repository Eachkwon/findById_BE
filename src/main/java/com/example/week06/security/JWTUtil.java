package com.example.week06.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;

public class JWTUtil {

    private static final String SECRET_KEY = "IKb3Krx6XaTF";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final long AUTH_TIME = 60 * 60;

    public static String getToken(UserDetailsImpl userDetailsImpl){
        return JWT.create()
                .withSubject(userDetailsImpl.getUsername())
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static String verify(String token){
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return verify.getSubject();
        }catch(Exception ex){
            return "";
        }
    }
}
