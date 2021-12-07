package iuh.kttkpm.nhom11.ulti;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import iuh.kttkpm.nhom11.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private static final String USER = "product_supplier_nhom_11";
    private static final String SECRET = "product_supplier_nhom_11_BaiTapLon_KienTrucPhanMem_HS256";
    private static final int EXPIRE_TIME = 86400000;

    public String generateToken(User user) {
        String token = null;
        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.claim(USER, user.getUsername());
            builder.expirationTime(generateExpirationDate());


            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            JWSSigner signer = new MACSigner(SECRET.getBytes());
            signedJWT.sign(signer);

            token = signedJWT.serialize();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return token;
    }

    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRE_TIME);
    }


    //--------------------getClaimsFromToken-------------------------
    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET.getBytes());
            if (signedJWT.verify(verifier)) {
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (JOSEException | ParseException e) {
            log.error(e.getMessage());
        }
        return claims;
    }

    //--------------------getUserFromToken-------------------------
    public String getUserFromToken(String token) {
        String user = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            if (claims != null && isTokenExpired(claims)) {
                user = claims.getStringClaim(USER);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return user;
    }

    //--------------------getExpirationDateFromToken-------------------------
    private Date getExpirationDateFromToken(JWTClaimsSet claims) {
        Date expiration = null;
        expiration = claims.getExpirationTime();
        return expiration;
    }

    //--------------------isTokenExpired-------------------------
    private boolean isTokenExpired(JWTClaimsSet claims) {
        log.error("token time life : "+ getExpirationDateFromToken(claims));
        log.error("time now : "+new Date());
        log.error("token exp "+String.valueOf(getExpirationDateFromToken(claims).after(new Date())));
        return getExpirationDateFromToken(claims).after(new Date());
    }

    public Boolean validateToken(String token, User user) {
        String username = getUserFromToken(token);
        log.error("jwt username : " + username + " " + token);
        log.error("user : "+ user.getUsername());
        if(!username.equals(user.getUsername()))
            return false;
        if (!isTokenExpired(getClaimsFromToken(token)))
            return false;
        return true;

    }
}
