package com.soongjamm.startboot.validator_json.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String name;
    @JsonFormat(pattern = "yyyy년 MM월 dd일 hh시 mm분 ss초")
    private LocalDateTime registerDate;
    @JsonIgnore
    private String password;
}

