package com.soongjamm.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

@RestController
public class HelloController {

    String key = "key";

    // 토큰 발급
    @PostMapping("/issue")
    public ResponseEntity<SessionResponseDto> issue(SessionRequestDto dto) {

        String jwt = Jwts.builder()
                .setSubject("users")
                .setAudience(dto.getName())
                .setIssuedAt(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDate.of(2021, Month.MARCH, 30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .claim("hello", "world")
                .signWith(
                        SignatureAlgorithm.HS256,
                        key.getBytes()
                )
                .compact();

        return ResponseEntity.ok().body(new SessionResponseDto(jwt));
    }

    // 토큰 확인이 되어야 리소스를 얻을 수 있다.
    @GetMapping("/hello")
    public Object hello(@RequestHeader(value="accessToken") String accessToken) {
        // 만약 변조된 토큰이면 파싱에 실패해서 SignatureException 발생
        // try-catch 로 예외처리가 필요하다.
        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(accessToken)
                .getBody();
        return claims;
    }
}
