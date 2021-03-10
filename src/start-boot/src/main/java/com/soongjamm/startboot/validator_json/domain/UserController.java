package com.soongjamm.startboot.validator_json.domain;

import com.soongjamm.startboot.validator_json.utils.ConfirmedPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmedPasswordValidator confirmedPasswordValidator;


    @PostMapping("/api/members/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequestDto dto) { // 파라미터로 Errors 를 받으면 ControllerAdvice 로 예외가 가지 않는다.
        confirmedPasswordValidator.validate(dto);
        Member input = Member.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .registerDate(LocalDateTime.now())
                .build();

        // 회원 가입
        Member newMember = userRepository.save(input);
        URI uri = URI.create("/api/members/" + newMember.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/api/members/all")
    public List<Member> membersAll() {
        return userRepository.findAll();
    }
}
