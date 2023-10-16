package com.trading.application.portfolio.controller;

import com.trading.application.logs.entity.AccessLog;
import com.trading.application.portfolio.entity.Portfolio;
import com.trading.application.portfolio.entity.PortfolioStocksRequest;
import com.trading.application.portfolio.service.PortfolioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = "http://localhost:8080")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<String> createPortfolio(@RequestBody Portfolio portfolio, HttpServletRequest request) {
        return portfolioService.createPortfolio(portfolio, request);
    }

    // get a portfolio
    @GetMapping
    @RequestMapping("/{portfolioId}")
    public Portfolio getPortfolio(@PathVariable String portfolioId) throws ExecutionException, InterruptedException {
        return portfolioService.getPortfolio(portfolioId);
    }

    // delete a portfolio
    @DeleteMapping
    @RequestMapping("delete/{portfolioId}")
    public ResponseEntity<String> deletePortfolio(@PathVariable String portfolioId) {
        return portfolioService.deletePortfolio(portfolioId);
    }

//    @PutMapping
//    @RequestMapping("/updateportfolio/")
//    public ResponseEntity<String> updatePortfolio(@RequestBody PortfolioStocksRequest portfolioStocksRequest, HttpServletRequest request) throws ExecutionException, InterruptedException {
//        return portfolioService.updatePortfolio(portfolioStocksRequest, request);
//    }

    @PutMapping
    @RequestMapping("updateportfolio")
    public ResponseEntity<String> updatePortfolio(@RequestBody Portfolio portfolio, HttpServletRequest request) throws ExecutionException,
            InterruptedException {
        return portfolioService.updatePortfolio(portfolio, request);
    }


    // get all portfolios of a customer
    @GetMapping
    @RequestMapping("/getportfolios/{userId}")
    public List<Portfolio> getAllPortfolios(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return portfolioService.getAllPortfolios(userId);
    }

    // get sectors of all stocks in a portfolio
    @GetMapping
    @RequestMapping("/getsectorsbyportfolio/{portfolioId}")
    public ResponseEntity<Map<String, Integer>> getSectorsByPortfolioId(@PathVariable String portfolioId) throws ExecutionException, InterruptedException {
        Map<String, Integer> sectorCounts = portfolioService.getSectorsByPortfolioId(portfolioId);
        if (sectorCounts != null) {
            return new ResponseEntity<>(sectorCounts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping("/getsectorsbyuser/{userId}")
    public ResponseEntity<Map<String, Integer>> getSectorsByUserId(@PathVariable String userId) throws ExecutionException, InterruptedException {
        Map<String, Integer> allSectorCounts = portfolioService.getSectorsByUserId(userId);
        if (allSectorCounts != null) {
            return new ResponseEntity<>(allSectorCounts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @RequestMapping("/gettotalportfoliovalue/{portfolioId}")
    public float getTotalPortfolioValue(@PathVariable String portfolioId) throws ExecutionException, InterruptedException {
        return portfolioService.getTotalPortfolioValue(portfolioId);
    }

    @GetMapping
    @RequestMapping("/getpublicportfolios")
    public ResponseEntity<ArrayList<Portfolio>> getAllPublicPortfolios() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(portfolioService.getAllPublicPortfolios(), HttpStatus.OK);
    }

}
