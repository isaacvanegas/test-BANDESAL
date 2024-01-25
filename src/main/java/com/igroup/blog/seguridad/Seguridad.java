package com.igroup.blog.seguridad;

import com.igroup.blog.utils.EncryptionMethod;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Seguridad  {



    public Seguridad() {
    }

    public Boolean checkpw(String plaintext, String hashed,EncryptionMethod encryptionMethod) {
        boolean result = false;
        if (encryptionMethod == EncryptionMethod.BCrypt) {
            result = BCrypt.checkpw(plaintext, hashed);
        }
        return result;
    }

    public String encode(String plaintext,EncryptionMethod encryptionMethod) {
        String hashed ="";
        if (encryptionMethod == EncryptionMethod.BCrypt) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashed = passwordEncoder.encode(plaintext);
        }
        return hashed;
    }


}
