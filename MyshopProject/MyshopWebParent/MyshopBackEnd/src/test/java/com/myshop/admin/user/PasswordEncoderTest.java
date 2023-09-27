package com.myshop.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456Aa!";
        String encodedPassword = passwordEncoder.encode((rawPassword));

        System.out.println(encodedPassword);

        boolean matches = passwordEncoder.matches(rawPassword,encodedPassword);
        assertThat(matches).isTrue();
    }
}
