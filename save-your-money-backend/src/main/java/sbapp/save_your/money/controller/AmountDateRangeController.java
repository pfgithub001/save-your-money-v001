package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.AmountDateRangeDTO;
import sbapp.save_your.money.service.AmountDateRangeService;

@RestController
@RequestMapping("/amountDateRange")
@CrossOrigin
public class AmountDateRangeController {

    private final AmountDateRangeService amountDateRangeService;

    public AmountDateRangeController(AmountDateRangeService amountDateRangeService) { 
        this.amountDateRangeService = amountDateRangeService; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<AmountDateRangeDTO>> getAllAmountDateRanges() {
        List<AmountDateRangeDTO> amountDateRangesDTO = amountDateRangeService.findAllAmountDateRanges();
        return new ResponseEntity<>(amountDateRangesDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AmountDateRangeDTO> getAmountDateRangeById(@PathVariable("id") Long id) {
        AmountDateRangeDTO amountDateRangeDTO = amountDateRangeService.findAmountDateRangeById(id);
        return new ResponseEntity<>(amountDateRangeDTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<AmountDateRangeDTO> addAmountDateRange(@RequestBody AmountDateRangeDTO amountDateRangeDTO) {
        AmountDateRangeDTO newAmountDateRangeDTO = amountDateRangeService.addAmountDateRange(amountDateRangeDTO);
        return new ResponseEntity<>(newAmountDateRangeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AmountDateRangeDTO> updateAmountDateRange(@RequestBody AmountDateRangeDTO amountDateRangeDTO) {
        AmountDateRangeDTO updatedAmountDateRangeDTO = amountDateRangeService.updateAmountDateRange(amountDateRangeDTO);
        return new ResponseEntity<>(updatedAmountDateRangeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAmountDateRange(@PathVariable("id") Long id) {
        amountDateRangeService.deleteAmountDateRange(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

