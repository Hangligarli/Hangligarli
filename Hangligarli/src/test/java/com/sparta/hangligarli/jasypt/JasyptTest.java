package com.sparta.hangligarli.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JasyptTest {

    @Test
    void jasypt_encrypt_decrypt_test(){
        String plainText = "jdbc:mysql://spartaa.cgzxqsxot3o2.ap-northeast-2.rds.amazonaws.com:3306/board?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("password");

        String encryptedText = jasypt.encrypt(plainText);
        String decryptedText = jasypt.decrypt(encryptedText);

        assertThat(decryptedText).isEqualTo(plainText);

        System.out.println(encryptedText);
        System.out.println(decryptedText);
    }

}
