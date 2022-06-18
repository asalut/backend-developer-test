package com.halter.herdservice.controller;

import com.halter.herdservice.model.Cow;
import com.halter.herdservice.model.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController(value = "cows")
public class CowController {

    @Autowired
    private CowRepository cowRepository;

    @PostMapping("/cows")
    private ResponseEntity<Cow> addCow(@RequestParam(name = "collarId", required = true) Long collarId,
                                      @RequestParam(name = "cowNumber", required = true) Long cowNumber) {
        return ResponseEntity.ok(cowRepository.save(new Cow(cowNumber)));
    }

    @PutMapping("/cows/{id}")
    private ResponseEntity<Cow> addCow(@PathVariable(name = "id", required = true) UUID id) {
        return ResponseEntity.ok(cowRepository.findById(id).get());
    }

    @GetMapping("/cows/{cowNumber}")
    private ResponseEntity<Cow> getCowByNumber(
            @PathVariable(name = "cowNumber", required = true) Long cowNumber) {
        return ResponseEntity.ok(cowRepository.findByCowNumber(cowNumber).get());
    }

    @GetMapping("/cows")
    private ResponseEntity<List<Cow>> getCows() {
        return ResponseEntity.ok(cowRepository.findAll());
    }
}
