package com.pblgllgs.stockmicroservice.controller;

import com.pblgllgs.stockmicroservice.entity.StockEntity;
import com.pblgllgs.stockmicroservice.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
public class StockController {

    private final StockRepository stockRepository;

    @RequestMapping("/{code}")
    public boolean stockAvailable(@PathVariable String code){
        Optional<StockEntity> stock = stockRepository.findByCode(code);
        stock.orElseThrow(() -> new RuntimeException("Cannot find the code"+  code));
        return stock.get().getQuantity() > 0;
    }
}
