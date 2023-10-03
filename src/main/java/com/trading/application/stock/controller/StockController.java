package com.trading.application.stock.controller;

import com.trading.application.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching
@RequestMapping("/stock")
@CrossOrigin(origins = "http://localhost:8080")
public class StockController {
    @Autowired
    private StockService stockService;

    // create new stock
//    @PostMapping
//    @RequestMapping("/create")
//    public String createStock(@RequestBody Stock stock) throws ExecutionException, InterruptedException {
//        return stockService.createStock(stock);
//    }

    // get stock by stockticker
//    @Cacheable(key="#stockTicker",value = "stockCache")
//    @GetMapping
//    @RequestMapping("/{stockTicker}")
//    public Stock getStockById(@PathVariable String stockTicker) throws ExecutionException, InterruptedException, JsonProcessingException {
//        System.out.println("Invoked cache");
//        return stockService.(stockTicker);
//    }

//    @GetMapping
//    @RequestMapping("/{stockTicker}/companyOverview")
//    public Stock getStockOverviewById(@PathVariable String stockTicker) throws ExecutionException, InterruptedException, JsonProcessingException {
//        return stockService.getStockOverview(stockTicker);
//    }


}
