package com.adrar.cdafad.service;

import com.adrar.cdafad.entity.Manufacturer;
import com.adrar.cdafad.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    //Méthodes du service
    //Méthode qui ajoute un Manufacturer
    public Manufacturer createManufacturer(Manufacturer manufacturer) throws Exception {

        if(this.manufacturerRepository.existsByNameAndConsole(manufacturer.getName(), manufacturer.getConsole())) {
            throw new Exception("Le Manufacturer avec le  name " + manufacturer.getName() + " et la " + manufacturer.getConsole() + " existe déja");
        }
        return manufacturerRepository.save(manufacturer);
    }

    //Méthode pour récupérer un Manufacturer
    public Manufacturer getManufacturerById(Integer id) throws Exception
    {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        //Test si il n'existe pas
        if (manufacturer.isEmpty()) {
            throw new Exception("Le Manufacturer n'existe pas");
        }
        return manufacturer.get();
    }

    //Méthode pour récupérer la liste des Manufacturer
    public Iterable<Manufacturer> getAllManufacturers() throws Exception
    {
        //Test si la liste est vide
        if (this.manufacturerRepository.count() == 0) {
            throw new Exception("La liste des Manufacturer est vide");
        }
        return manufacturerRepository.findAll();
    }
}
