package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import sbapp.save_your.money.entity.FixedMovement;
import sbapp.save_your.money.service.FixedMovementService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/fixedMovement")
@CrossOrigin

public class FixedMovementController {

    public final FixedMovementService fixedMovementService;

    public FixedMovementController(FixedMovementService fixedMovementService) { this.fixedMovementService = fixedMovementService; }

    @GetMapping("/all")
    public ResponseEntity<List<FixedMovement>> getAllFixedMovements() {
        List<FixedMovement> fixedMovements = fixedMovementService.findAllFixedMovements();
        return new ResponseEntity<>(fixedMovements, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FixedMovement> getFixedMovementById(@PathVariable("id") Long id) {
        FixedMovement fixedMovement = fixedMovementService.findFixedMovementById(id);
        return new ResponseEntity<>(fixedMovement, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<FixedMovement> addFixedMovement(@RequestBody FixedMovement fixedMovement) {
        FixedMovement newFixedMovement = fixedMovementService.addFixedMovement(fixedMovement);
        return new ResponseEntity<>(newFixedMovement, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<FixedMovement> updateFixedMovement(@RequestBody FixedMovement fixedMovement) {
        FixedMovement updateFixedMovement = fixedMovementService.updateFixedMovement(fixedMovement);
        return new ResponseEntity<>(updateFixedMovement, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFixedMovement(@PathVariable("id") Long id) {
        fixedMovementService.deleteFixedMovement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
