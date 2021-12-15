package com.example.tobpring.chap03.templatemethod;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CalculatorTest {

    Calculator calculator;
    String path;

    @BeforeEach
    void setup() {
        this.calculator = new Calculator();
        this.path = getClass().getResource("number.txt").getPath();
    }

    @Test
    void sum() throws IOException {
        Integer sum = calculator.sum(path);
        Assertions.assertThat(sum).isEqualTo(10);
    }

    @Test
    void multiply() throws IOException {
        Integer multiply = calculator.multiply(path);
        Assertions.assertThat(multiply).isEqualTo(24);
    }

    @Test
    void concatenate() throws IOException {
        String concat = calculator.concatenate(path);
        Assertions.assertThat(concat).isEqualTo("1234");
    }
}