package org.example.chery_payment.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.chery_payment.entity.Chery;
import org.example.chery_payment.service.api.CheryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class CheryController {
    private final CheryService cheryService;

    @PostMapping("/chery/new")
    public Chery save(@RequestBody Chery chery) {
        return cheryService.save(chery);
    }

    @GetMapping("/chery/all")
    public List<Chery> getAll() {
        return cheryService.findAll();
    }

    @GetMapping("/chery/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Chery chery = cheryService.findById(id);
            return ResponseEntity.ok(chery);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Chery updatedChery) {
        try {
            Chery existingChery = cheryService.findById(id);
            existingChery.setNumber(updatedChery.getNumber());

            Chery savedChery = cheryService.save(existingChery);
            return ResponseEntity.ok(savedChery);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
