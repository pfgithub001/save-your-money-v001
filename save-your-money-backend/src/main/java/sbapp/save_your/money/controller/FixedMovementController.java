package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.FixedMovementDTO;
import sbapp.save_your.money.service.FixedMovementService;

@RestController
@RequestMapping("/fixedMovement")
@CrossOrigin
public class FixedMovementController {

    private final FixedMovementService fixedMovementService;

    public FixedMovementController(FixedMovementService fixedMovementService) { 
        this.fixedMovementService = fixedMovementService; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<FixedMovementDTO>> getAllFixedMovements() {
        List<FixedMovementDTO> fixedMovementsDTO = fixedMovementService.findAllFixedMovements();
        return new ResponseEntity<>(fixedMovementsDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FixedMovementDTO> getFixedMovementById(@PathVariable("id") Long id) {
        FixedMovementDTO fixedMovementDTO = fixedMovementService.findFixedMovementById(id);
        return new ResponseEntity<>(fixedMovementDTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<FixedMovementDTO> addFixedMovement(@RequestBody FixedMovementDTO fixedMovementDTO) {
        FixedMovementDTO newFixedMovementDTO = fixedMovementService.addFixedMovement(fixedMovementDTO);
        return new ResponseEntity<>(newFixedMovementDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<FixedMovementDTO> updateFixedMovement(@RequestBody FixedMovementDTO fixedMovementDTO) {
        FixedMovementDTO updatedFixedMovementDTO = fixedMovementService.updateFixedMovement(fixedMovementDTO);
        return new ResponseEntity<>(updatedFixedMovementDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFixedMovement(@PathVariable("id") Long id) {
        fixedMovementService.deleteFixedMovement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

