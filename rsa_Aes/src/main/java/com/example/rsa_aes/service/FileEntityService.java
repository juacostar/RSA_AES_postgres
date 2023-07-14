package com.example.rsa_aes.service;

import com.example.rsa_aes.model.FileEntity;
import com.example.rsa_aes.repository.FileEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;

@Service
public class FileEntityService {

    @Autowired
    private FileEntityRepository fileEntityRepository;

    public void processFile(String path) throws NoSuchAlgorithmException {
        File file = new File(path);

        //generate key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecretKey secretKey = keyGenerator.generateKey();

        // cipher file
        try {
            byte[] encrypted = encryptData(file, secretKey);

            // generating RSA key par
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom();
            keyPairGenerator.initialize(2048, secureRandom);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            byte[] signed = signData(encrypted, keyPair.getPrivate());

            FileEntity fileEntity =  new FileEntity();
            fileEntity.setEncrypted(encrypted);
            fileEntity.setSigned(signed);

            fileEntityRepository.save(fileEntity);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public byte[] encryptData(File file, SecretKey secretKey) throws FileNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] fileData = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(fileData);

        return encrypted;
    }

    public byte[] signData(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        signature.update(data);

        return signature.sign();

    }

}
