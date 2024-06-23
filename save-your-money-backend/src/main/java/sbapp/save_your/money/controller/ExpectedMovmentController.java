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

import sbapp.save_your.money.entity.ExpectedMovment;
import sbapp.save_your.money.service.ExpectedMovmentService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/expectedMovment")
@CrossOrigin

public class ExpectedMovmentController {

    public final ExpectedMovmentService expectedMovmentService;

    public ExpectedMovmentController(ExpectedMovmentService expectedMovmentService) { this.expectedMovmentService = expectedMovmentService; }

    @GetMapping("/all")
    public ResponseEntity<List<ExpectedMovment>> getAllExpectedMovments() {
        List<ExpectedMovment> expectedMovments = expectedMovmentService.findAllExpectedMovments();
        return new ResponseEntity<>(expectedMovments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ExpectedMovment> getExpectedMovmentById(@PathVariable("id") Long id) {
        ExpectedMovment expectedMovment = expectedMovmentService.findExpectedMovmentById(id);
        return new ResponseEntity<>(expectedMovment, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<ExpectedMovment> addExpectedMovment(@RequestBody ExpectedMovment expectedMovment) {
        ExpectedMovment newExpectedMovment = expectedMovmentService.addExpectedMovment(expectedMovment);
        return new ResponseEntity<>(newExpectedMovment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ExpectedMovment> updateExpectedMovment(@RequestBody ExpectedMovment expectedMovment) {
        ExpectedMovment updateExpectedMovment = expectedMovmentService.updateExpectedMovment(expectedMovment);
        return new ResponseEntity<>(updateExpectedMovment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpectedMovment(@PathVariable("id") Long id) {
        expectedMovmentService.deleteExpectedMovment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
