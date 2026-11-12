package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Provedor;

public interface ProvedorRepository extends JpaRepository<Provedor, Long> {
    Optional<Provedor> findByEmail(String email);
}
