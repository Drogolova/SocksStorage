package com.example.socksStorage.controller;

import com.example.socksStorage.dto.SocksDTO;
import com.example.socksStorage.service.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;


    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/add")
    public ResponseEntity<SocksDTO> addSocks(@RequestBody SocksDTO socksDTO) {
        SocksDTO savedSocksDTO = socksService.addSocks(socksDTO);
        return ResponseEntity.ok(savedSocksDTO);
    }

    @PostMapping("/release")
    public ResponseEntity<SocksDTO> releaseSocks(@RequestBody SocksDTO socksDTO) {
        SocksDTO savedSocksDTO = socksService.releaseSocks(socksDTO);
        return ResponseEntity.ok(savedSocksDTO);
    }
}
