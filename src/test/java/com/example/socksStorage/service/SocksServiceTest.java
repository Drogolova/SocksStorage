package com.example.socksStorage.service;

import com.example.socksStorage.dto.SocksDTO;
import com.example.socksStorage.entity.Color;
import com.example.socksStorage.entity.Size;
import com.example.socksStorage.entity.Socks;
import com.example.socksStorage.exception.IdNullSocksException;
import com.example.socksStorage.exception.NotFoundSocksException;
import com.example.socksStorage.exception.OutOfQuantitySocksException;
import com.example.socksStorage.repository.SocksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SocksServiceTest {

    @Autowired
    private SocksService socksService;
    @Autowired
    private SocksRepository socksRepository;

    @BeforeEach
    public void init() {
        socksRepository.deleteAll();
    }

    @Test
    void shouldSaveNewSocks() {
        SocksDTO socks = returnSocksDTO();
        socks.setPartNumber(null);
        final SocksDTO savedSocksDTO = socksService.addSocks(socks);
        assertTrue(socksRepository.findById(savedSocksDTO.getPartNumber()).isPresent());
    }

    @Test
    void shouldUpdateSocks() {
        SocksDTO socks = returnSocksDTO();
        socks.setPartNumber(null);
        final SocksDTO savedSocksDTO = socksService.addSocks(socks);
        assertTrue(socksRepository.findById(savedSocksDTO.getPartNumber()).isPresent());

        SocksDTO socks2 = returnSocksDTO();
        socks2.setPartNumber(savedSocksDTO.getPartNumber());
        socks2.setQuantity(10);
        socksService.addSocks(socks2);
        final Socks socksInBd = socksRepository.findById(socks2.getPartNumber()).get();
        assertEquals(socks.getQuantity() + socks2.getQuantity(), socksInBd.getQuantity());
    }

    @Test
    void shouldReturnNotFoundException() {
        assertThrows(NotFoundSocksException.class, () -> socksService.addSocks(returnSocksDTO()));
    }

    @Test
    void shouldThrowIdNullExceptionInReleaseSocks() {
        SocksDTO socksDTO = returnSocksDTO();
        socksDTO.setPartNumber(null);
        assertThrows(IdNullSocksException.class, () -> socksService.releaseSocks(socksDTO));
    }

    @Test
    void shouldThrowOutOfQuantitySocksExceptionInReleaseSocks() {
        final Socks socks = returnSocks();
        final Socks saved = socksRepository.save(socks);
        SocksDTO socksDTO = returnSocksDTO();
        socksDTO.setPartNumber(saved.getId());
        socksDTO.setQuantity(socks.getQuantity() + 1);
        assertThrows(OutOfQuantitySocksException.class, () -> socksService.releaseSocks(socksDTO));
    }

    @Test
    void shouldReleaseSocks() {
        final Socks socks = returnSocks();
        final Socks saved = socksRepository.save(socks);
        SocksDTO socksDTO = returnSocksDTO();
        socksDTO.setPartNumber(saved.getId());
        socksDTO.setQuantity(socks.getQuantity() - 1);
        socksService.releaseSocks(socksDTO);
        final Socks socks2 = socksRepository.findById(saved.getId()).get();
        assertEquals(socks2.getQuantity(), 1);
    }

    Socks returnSocks() {
        Socks socks = new Socks();
        socks.setId(1L);
        socks.setColor(Color.BLACK);
        socks.setSize(Size.SIZE_36);
        socks.setCottonPart(10);
        socks.setQuantity(20);
        return socks;
    }

    SocksDTO returnSocksDTO() {
        SocksDTO socks = new SocksDTO();
        socks.setPartNumber(1L);
        socks.setColor(Color.BLACK);
        socks.setSize(Size.SIZE_36);
        socks.setCottonPart(10);
        socks.setQuantity(20);
        return socks;
    }
}