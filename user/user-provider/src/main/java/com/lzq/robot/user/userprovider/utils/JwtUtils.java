package com.lzq.robot.user.userprovider.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class JwtUtils {


    public void encode() {
        RSAPublicKey publicKey = null; //Get the key instance
        RSAPrivateKey privateKey = null; //Get the key instance
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }


    public void verify() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        RSAPublicKey publicKey = null;//Get the key instance
        RSAPrivateKey privateKey = null;//Get the key instance
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
        }

    }

    public void decode(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        try {
            DecodedJWT jwt = JWT.decode(token);
        } catch (JWTDecodeException exception){
            //Invalid token
        }
    }
}
