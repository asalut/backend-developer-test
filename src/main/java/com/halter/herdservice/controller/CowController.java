package com.halter.herdservice.controller;

import com.halter.herdservice.model.bean.CowRequest;
import com.halter.herdservice.model.bean.CowResponse;
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
}
