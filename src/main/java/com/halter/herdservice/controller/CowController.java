package com.halter.herdservice.controller;

import com.halter.herdservice.model.Cow;
import com.halter.herdservice.model.bean.CowRequest;
import com.halter.herdservice.model.bean.CowResponse;
import com.halter.herdservice.model.repository.CowRepository;
import com.halter.herdservice.service.CowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cows")
public class CowController {

    @Autowired
    private CowRepository cowRepository;

    @Autowired
    private CowService cowService;

    @PostMapping
    private ResponseEntity<CowResponse> addCow(@RequestBody(required = true) CowRequest request) {
        return ResponseEntity.ok(cowService.addCow(request));
    }

    @PutMapping("/{id}")
    private ResponseEntity<Cow> addCow(@PathVariable(name = "id", required = true) UUID id) {
        return ResponseEntity.ok(cowRepository.findById(id).get());
    }

    @GetMapping("/{collarId}")
    private ResponseEntity<CowResponse> getCowByNumber(
            @PathVariable(name = "collarId", required = true) String collarId) {
        return ResponseEntity.ok(cowService.getCowByCollarId(collarId));
    }

    @GetMapping
    private ResponseEntity<List<Cow>> getCows() {
        return ResponseEntity.ok(cowRepository.findAll());
    }
}
