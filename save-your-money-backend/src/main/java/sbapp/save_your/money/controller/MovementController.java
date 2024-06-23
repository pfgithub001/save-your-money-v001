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

import sbapp.save_your.money.entity.Movement;
import sbapp.save_your.money.service.MovementService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/movement")
@CrossOrigin

public class MovementController {

    public final MovementService movementService;

    public MovementController(MovementService movementService) { this.movementService = movementService; }

    @GetMapping("/all")
    public ResponseEntity<List<Movement>> getAllMovements() {
        List<Movement> movements = movementService.findAllMovements();
        return new ResponseEntity<>(movements, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable("id") Long id) {
        Movement movement = movementService.findMovementById(id);
        return new ResponseEntity<>(movement, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<Movement> addMovement(@RequestBody Movement movement) {
        Movement newMovement = movementService.addMovement(movement);
        return new ResponseEntity<>(newMovement, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Movement> updateMovement(@RequestBody Movement movement) {
        Movement updateMovement = movementService.updateMovement(movement);
        return new ResponseEntity<>(updateMovement, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovement(@PathVariable("id") Long id) {
        movementService.deleteMovement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
