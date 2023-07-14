package com.example.rsa_aes.repository;

import com.example.rsa_aes.model.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepository extends CrudRepository<FileEntity, Integer> {
}
