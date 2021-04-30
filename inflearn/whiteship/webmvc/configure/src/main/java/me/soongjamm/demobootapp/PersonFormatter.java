package me.soongjamm.demobootapp;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PersonFormatter implements Formatter<Person> {

    @Override // 서버로 넘어온 스트링을 어떻게 변환할 것인가
    public Person parse(String s, Locale locale) throws ParseException {
        Person person = new Person();
        person.setName(s);
        return person;
    }

    @Override // 객체를 어떻게 출력해줄 것인가
    public String print(Person person, Locale locale) {
        return person.toString();
    }
}
