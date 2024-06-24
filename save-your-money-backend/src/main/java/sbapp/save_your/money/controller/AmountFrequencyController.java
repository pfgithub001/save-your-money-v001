package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.AmountFrequencyDTO;
import sbapp.save_your.money.service.AmountFrequencyService;

@RestController
@RequestMapping("/amountFrequency")
@CrossOrigin
public class AmountFrequencyController {

    private final AmountFrequencyService amountFrequencyService;

    public AmountFrequencyController(AmountFrequencyService amountFrequencyService) { 
        this.amountFrequencyService = amountFrequencyService; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<AmountFrequencyDTO>> getAllAmountFrequencies() {
        List<AmountFrequencyDTO> amountFrequenciesDTO = amountFrequencyService.findAllAmountFrequencies();
        return new ResponseEntity<>(amountFrequenciesDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AmountFrequencyDTO> getAmountFrequencyById(@PathVariable("id") Long id) {
        AmountFrequencyDTO amountFrequencyDTO = amountFrequencyService.findAmountFrequencyById(id);
        return new ResponseEntity<>(amountFrequencyDTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<AmountFrequencyDTO> addAmountFrequency(@RequestBody AmountFrequencyDTO amountFrequencyDTO) {
        AmountFrequencyDTO newAmountFrequencyDTO = amountFrequencyService.addAmountFrequency(amountFrequencyDTO);
        return new ResponseEntity<>(newAmountFrequencyDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AmountFrequencyDTO> updateAmountFrequency(@RequestBody AmountFrequencyDTO amountFrequencyDTO) {
        AmountFrequencyDTO updatedAmountFrequencyDTO = amountFrequencyService.updateAmountFrequency(amountFrequencyDTO);
        return new ResponseEntity<>(updatedAmountFrequencyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAmountFrequency(@PathVariable("id") Long id) {
        amountFrequencyService.deleteAmountFrequency(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

