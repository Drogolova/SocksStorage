package com.example.socksStorage.repository;

import com.example.socksStorage.entity.Color;
import com.example.socksStorage.entity.Size;
import com.example.socksStorage.entity.Socks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SocksRepositoryTest {

    @Autowired
    private SocksRepository socksRepository;


    @Test
    void shouldSaveAndReturnEntity() {
        Socks socks = new Socks();
        socks.setColor(Color.BLACK);
        socks.setSize(Size.SIZE_36);
        socks.setCottonPart(10);
        socks.setQuantity(20);

        socks = socksRepository.save(socks);
        assertTrue(socksRepository.findById(socks.getId()).isPresent());
    }
}