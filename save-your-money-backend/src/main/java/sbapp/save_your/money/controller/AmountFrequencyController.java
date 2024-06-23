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

import sbapp.save_your.money.entity.AmountFrequency;
import sbapp.save_your.money.service.AmountFrequencyService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/amountFrequency")
@CrossOrigin

public class AmountFrequencyController {

    public final AmountFrequencyService amountFrequencyService;

    public AmountFrequencyController(AmountFrequencyService amountFrequencyService) { this.amountFrequencyService = amountFrequencyService; }

    @GetMapping("/all")
    public ResponseEntity<List<AmountFrequency>> getAllAmountFrequencies() {
        List<AmountFrequency> amountFrequencies = amountFrequencyService.findAllAmountFrequencies();
        return new ResponseEntity<>(amountFrequencies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AmountFrequency> getAmountFrequencyById(@PathVariable("id") Long id) {
        AmountFrequency amountFrequency = amountFrequencyService.findAmountFrequencyById(id);
        return new ResponseEntity<>(amountFrequency, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<AmountFrequency> addAmountFrequency(@RequestBody AmountFrequency amountFrequency) {
        AmountFrequency newAmountFrequency = amountFrequencyService.addAmountFrequency(amountFrequency);
        return new ResponseEntity<>(newAmountFrequency, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AmountFrequency> updateAmountFrequency(@RequestBody AmountFrequency amountFrequency) {
        AmountFrequency updateAmountFrequency = amountFrequencyService.updateAmountFrequency(amountFrequency);
        return new ResponseEntity<>(updateAmountFrequency, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAmountFrequency(@PathVariable("id") Long id) {
        amountFrequencyService.deleteAmountFrequency(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
