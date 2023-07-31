package com.example.socksStorage.service;

import com.example.socksStorage.dto.SocksDTO;
import com.example.socksStorage.entity.Socks;
import com.example.socksStorage.exception.IdNullSocksException;
import com.example.socksStorage.exception.NotFoundSocksException;
import com.example.socksStorage.exception.OutOfQuantitySocksException;
import com.example.socksStorage.repository.SocksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;

    public SocksService(SocksRepository socksRepository, SocksMapper socksMapper) {
        this.socksRepository = socksRepository;
        this.socksMapper = socksMapper;
    }

//    Добавление носков на склад
//    Если добавляемых носков на складе еще нет, создаём новую сущность и присваеваем id
//    Если такие носки уже есть, добавляем количество
    public SocksDTO addSocks(SocksDTO socksDTO) {
        final Socks newSocks = socksMapper.toEntity(socksDTO);
        if (newSocks.getId() == null) {
            return socksMapper.toDTO(socksRepository.save(newSocks));
        }
        final Optional<Socks> socksDb = socksRepository.findById(newSocks.getId());
        if (socksDb.isPresent()) {
            Socks socks = socksDb.get();
            socks.setQuantity(socks.getQuantity() + newSocks.getQuantity());
            return socksMapper.toDTO(socksRepository.save(socks));
        } else {
            throw new NotFoundSocksException();
        }
    }

    //Отпуск носков со склада
    //Если запрос корректный уменьшаем количиство
    //Если отпускаемых носков не существует или существуют в количестве меньшем, чем нужно отпустить, выбрасываем соответствующее исключение
    public SocksDTO releaseSocks(SocksDTO socksDTO) {
        final Socks newSocks = socksMapper.toEntity(socksDTO);
        if (newSocks.getId() == null) {
            throw new IdNullSocksException();
        }
        final Optional<Socks> socksDb = socksRepository.findById(newSocks.getId());
        if (socksDb.isPresent()) {
            Socks socks = socksDb.get();
            if (newSocks.getQuantity() > socks.getQuantity()) {
                throw new OutOfQuantitySocksException();
            }
            socks.setQuantity(socks.getQuantity() - newSocks.getQuantity());
            return socksMapper.toDTO(socksRepository.save(socks));
        } else {
            throw new NotFoundSocksException();
        }
    }
}
