package com.example.socksStorage.service;

import com.example.socksStorage.dto.SocksDTO;
import com.example.socksStorage.entity.Socks;
import org.springframework.stereotype.Service;

@Service
public class SocksMapper {

    public Socks toEntity(SocksDTO socksDTO) {
        return new Socks(socksDTO.getPartNumber(), socksDTO.getColor(), socksDTO.getSize(), socksDTO.getCottonPart(), socksDTO.getQuantity());
    }

    public SocksDTO toDTO(Socks socks) {
        return new SocksDTO(socks.getId(), socks.getColor(), socks.getSize(), socks.getCottonPart(), socks.getQuantity());
    }
}
