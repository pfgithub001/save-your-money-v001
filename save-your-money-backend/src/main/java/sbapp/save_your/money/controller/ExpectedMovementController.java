package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.ExpectedMovementDTO;
import sbapp.save_your.money.service.ExpectedMovementService;

@RestController
@RequestMapping("/expectedMovement")
@CrossOrigin
public class ExpectedMovementController {

    private final ExpectedMovementService expectedMovementService;

    public ExpectedMovementController(ExpectedMovementService expectedMovementService) { 
        this.expectedMovementService = expectedMovementService; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpectedMovementDTO>> getAllExpectedMovements() {
        List<ExpectedMovementDTO> expectedMovementsDTO = expectedMovementService.findAllExpectedMovements();
        return new ResponseEntity<>(expectedMovementsDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ExpectedMovementDTO> getExpectedMovementById(@PathVariable("id") Long id) {
        ExpectedMovementDTO expectedMovementDTO = expectedMovementService.findExpectedMovementById(id);
        return new ResponseEntity<>(expectedMovementDTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<ExpectedMovementDTO> addExpectedMovement(@RequestBody ExpectedMovementDTO expectedMovementDTO) {
        ExpectedMovementDTO newExpectedMovementDTO = expectedMovementService.addExpectedMovement(expectedMovementDTO);
        return new ResponseEntity<>(newExpectedMovementDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ExpectedMovementDTO> updateExpectedMovement(@RequestBody ExpectedMovementDTO expectedMovementDTO) {
        ExpectedMovementDTO updatedExpectedMovementDTO = expectedMovementService.updateExpectedMovement(expectedMovementDTO);
        return new ResponseEntity<>(updatedExpectedMovementDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpectedMovement(@PathVariable("id") Long id) {
        expectedMovementService.deleteExpectedMovement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

