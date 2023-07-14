package com.example.rsa_aes.model;

import javax.persistence.*;

@Entity
@Table(name = "File_entity")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "encrypted")
    private byte[] encrypted;

    @Lob
    @Column(name = "signed")
    private byte[] signed;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(byte[] encrypted) {
        this.encrypted = encrypted;
    }

    public byte[] getSigned() {
        return signed;
    }

    public void setSigned(byte[] signed) {
        this.signed = signed;
    }
}
