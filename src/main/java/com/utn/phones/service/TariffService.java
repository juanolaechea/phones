package com.utn.phones.service;

import com.utn.phones.domain.City;
import com.utn.phones.domain.Tariff;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.BandRepository;
import com.utn.phones.persistence.TariffRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TariffService {

    private TariffRepository tariffRepository;
    private CityService cityService;
    private BandRepository bandRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository, CityService cityService) {
        this.tariffRepository = tariffRepository;
        this.cityService = cityService;
    }

    public List<Tariff> getAllTariff() {
        return this.tariffRepository.findAll();
    }

    public Tariff getTariffByCities(String codeO, String codeD)throws ElementDoesNotExistsException {
        try{
            City o= this.cityService.findByCode(codeO);
            City d= this.cityService.findByCode(codeD);
            return this.tariffRepository.findByCityOriginAndCityDestination(o,d);
        }
        catch (ElementDoesNotExistsException e) {
            throw new ElementDoesNotExistsException();
        }
    }


    @Service
    public static class JwtService {
        private static final int EXPIRATION_TIME = 1000 * 60 * 60;
        private static final String AUTHORITIES = "authorities";
        private final String SECRET_KEY;

        public JwtService() {
            SECRET_KEY = Base64.getEncoder().encodeToString("key".getBytes());
        }

        public String createToken(UserDetails userDetails) {
            String username = userDetails.getUsername();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            return Jwts.builder()
                    .setSubject(username)
                    .claim(AUTHORITIES, authorities)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        }

        public Boolean hasTokenExpired(String token) {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        }

        public Boolean validateToken(String token, UserDetails userDetails) {
            String username = extractUsername(token);
            return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));

        }

        public String extractUsername(String token) {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public Collection<? extends GrantedAuthority> getAuthorities(String token) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
        }

    }
}
