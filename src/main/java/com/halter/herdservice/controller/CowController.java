package com.halter.herdservice.controller;

import com.halter.herdservice.model.bean.CowRequest;
import com.halter.herdservice.model.bean.CowResponse;
import com.halter.herdservice.service.CowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cows")
public class CowController {

    private final Logger logger = LoggerFactory.getLogger(CowController.class);

    @Autowired
    private CowService cowService;

    @PostMapping
    private ResponseEntity<CowResponse> addCow(@RequestBody(required = true) CowRequest request) {
        return ResponseEntity.ok(cowService.addCow(request));
    }

    @PutMapping("/{id}")
    private ResponseEntity<CowResponse> updateCow(
            @PathVariable(name = "id", required = true) String cowId,
            @RequestBody(required = true) CowRequest request) throws Exception {
        return ResponseEntity.ok(cowService.updateCow(cowId, request));
    }

    @GetMapping
    private ResponseEntity<List<CowResponse>> getCows() {
        return ResponseEntity.ok(cowService.getAllCows());
    }

    @GetMapping("/{collarId}")
    private ResponseEntity<CowResponse> getCowByCollarId(
            @PathVariable(name = "collarId", required = true) String collarId) {
        return ResponseEntity.ok(cowService.getCowByCollarId(collarId));
    }

    @DeleteMapping("/{id}")
    // @CacheEvict(value = "cows", key = "#id")
    private ResponseEntity<String> deleteCow(
            @PathVariable(name = "id", required = true) String id) {

        return ResponseEntity.ok("ok");
    }
}
