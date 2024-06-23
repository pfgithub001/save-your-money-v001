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

import sbapp.save_your.money.entity.AmountDateRange;
import sbapp.save_your.money.service.AmountDateRangeService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/amountDateRange")
@CrossOrigin

public class AmountDateRangeController {

    public final AmountDateRangeService amountDateRangeService;

    public AmountDateRangeController(AmountDateRangeService amountDateRangeService) { this.amountDateRangeService = amountDateRangeService; }

    @GetMapping("/all")
    public ResponseEntity<List<AmountDateRange>> getAllAmountDateRanges() {
        List<AmountDateRange> amountDateRanges = amountDateRangeService.findAllAmountDateRanges();
        return new ResponseEntity<>(amountDateRanges, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AmountDateRange> getAmountDateRangeById(@PathVariable("id") Long id) {
        AmountDateRange amountDateRange = amountDateRangeService.findAmountDateRangeById(id);
        return new ResponseEntity<>(amountDateRange, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<AmountDateRange> addAmountDateRange(@RequestBody AmountDateRange amountDateRange) {
        AmountDateRange newAmountDateRange = amountDateRangeService.addAmountDateRange(amountDateRange);
        return new ResponseEntity<>(newAmountDateRange, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AmountDateRange> updateAmountDateRange(@RequestBody AmountDateRange amountDateRange) {
        AmountDateRange updateAmountDateRange = amountDateRangeService.updateAmountDateRange(amountDateRange);
        return new ResponseEntity<>(updateAmountDateRange, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAmountDateRange(@PathVariable("id") Long id) {
        amountDateRangeService.deleteAmountDateRange(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
