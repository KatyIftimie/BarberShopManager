package com.codecool.barbershop.barbershop;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/add-data")
public class DataInserterController {

    private DataInserter dataInserter;
    @GetMapping("/statuses")
    public void addData(){dataInserter.addStatuses();

    }
}
