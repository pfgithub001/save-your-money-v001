package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.MovementDTO;
import sbapp.save_your.money.service.MovementService;

@RestController
@RequestMapping("/movement")
@CrossOrigin
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) { 
        this.movementService = movementService; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovementDTO>> getAllMovements() {
        List<MovementDTO> movementsDTO = movementService.findAllMovements();
        return new ResponseEntity<>(movementsDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MovementDTO> getMovementById(@PathVariable("id") Long id) {
        MovementDTO movementDTO = movementService.findMovementById(id);
        return new ResponseEntity<>(movementDTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<MovementDTO> addMovement(@RequestBody MovementDTO movementDTO) {
        MovementDTO newMovementDTO = movementService.addMovement(movementDTO);
        return new ResponseEntity<>(newMovementDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MovementDTO> updateMovement(@RequestBody MovementDTO movementDTO) {
        MovementDTO updatedMovementDTO = movementService.updateMovement(movementDTO);
        return new ResponseEntity<>(updatedMovementDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovement(@PathVariable("id") Long id) {
        movementService.deleteMovement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

