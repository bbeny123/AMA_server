package org.beny.ama.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class CryptoUtil {

    private final static Gson gson = new Gson();
    private static BytesEncryptor aes;

    @Autowired
    private CryptoUtil(@Value("${crypto.aes.key:123456789abcdf}") String key, @Value("${crypto.aes.salt:fdcba987654321}") String salt) {
        CryptoUtil.aes = Encryptors.standard(key, salt);
    }

    public static String encrypt(Object object) {
        return DatatypeConverter.printBase64Binary(aes.encrypt(gson.toJson(object).getBytes(UTF_8)));
    }

    public static <T> T decrypt(String data, Class<T> targetClass) {
        return gson.fromJson(new String(aes.decrypt(DatatypeConverter.parseBase64Binary(data)), UTF_8), targetClass);
    }

}
