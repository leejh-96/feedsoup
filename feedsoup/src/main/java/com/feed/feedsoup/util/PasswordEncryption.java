package com.feed.feedsoup.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.BasicPasswordEncryptor;

@Slf4j
public class PasswordEncryption {

        public static String encryptPassword(String password) {
            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            String encryptedPassword = passwordEncryptor.encryptPassword(password);
            return encryptedPassword;
        }

        public static boolean checkPassword(String password, String encryptedPassword) {
            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            return passwordEncryptor.checkPassword(password, encryptedPassword);
        }
        public static boolean checkUserPw(String pw,String encPw){
            return checkMethod(pw, encPw);
        }

        private static boolean checkMethod(String pw, String encPw) {
            try {
                boolean passwordMatch = PasswordEncryption.checkPassword(pw, encPw);
                log.info("pw={}", pw);
                log.info("passwordMatch={}", passwordMatch);

                if (passwordMatch) {
                    return true;
                }
            } catch (EncryptionOperationNotPossibleException ex) {
                // 암호화된 비밀번호와 입력된 비밀번호가 일치하지 않는 경우
                return false;
            }
            return false;
        }
    }

    /*정적(static) 메서드를 사용하면 간편하게 의존성을 해결할 수 있지만,
    클래스 간의 결합도가 높아지고 테스트 및 재사용성 측면에서 제약이 따를 수 있습니다.
    따라서 일반적으로 생성자 주입을 선호하며, 정적 메서드는 특정 상황에서만 사용하는 것이 좋음.*/

