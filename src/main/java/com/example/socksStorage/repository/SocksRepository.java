package com.example.socksStorage.repository;

import com.example.socksStorage.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocksRepository extends JpaRepository<Socks, Long> {
}
