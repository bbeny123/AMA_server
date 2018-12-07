package org.beny.ama.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class EncryptionUtil {

    private final static Gson gson = new Gson();
    private static BytesEncryptor encryptor;

    @Autowired
    private EncryptionUtil(@Value("${encryption.aes.key:beaa54cdcfdef22f}") String key, @Value("${encryption.aes.salt:9988e4af7d75a20e}") String salt) {
        EncryptionUtil.encryptor = Encryptors.standard(key, salt);
    }

    public static String encrypt(Object object) {
        return new String(encryptor.encrypt(gson.toJson(object).getBytes(UTF_8)));
    }


}
