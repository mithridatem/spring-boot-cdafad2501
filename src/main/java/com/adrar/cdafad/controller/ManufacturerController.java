package com.adrar.cdafad.controller;

import com.adrar.cdafad.entity.Manufacturer;
import com.adrar.cdafad.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ManufacturerController {

    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturer/{id}")
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable Integer id) throws  Exception
    {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<Iterable<Manufacturer>> getAllManufacturers() throws  Exception
    {
        return new ResponseEntity<>(manufacturerService.getAllManufacturers(), HttpStatus.OK);
    }

    @PostMapping("/manufacturer")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer) throws Exception
    {
        Manufacturer newManufacturer = this.manufacturerService.createManufacturer(manufacturer);
        return new ResponseEntity<>(newManufacturer, HttpStatus.CREATED);
    }
}
