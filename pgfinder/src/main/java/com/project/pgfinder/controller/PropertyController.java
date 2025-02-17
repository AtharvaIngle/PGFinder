package com.project.pgfinder.controller;

import com.project.pgfinder.entity.Property;
import com.project.pgfinder.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        return propertyService.addProperty(property);
    }

    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return "Property deleted successfully!";
    }

    @GetMapping("/location/{location}")
    public List<Property> getPropertiesByLocation(@PathVariable String location) {
        return propertyService.getPropertiesByLocation(location);
    }
}