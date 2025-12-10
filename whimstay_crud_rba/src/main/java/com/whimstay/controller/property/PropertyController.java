package com.whimstay.controller.property;
import com.whimstay.dto.PropertyDto;
import com.whimstay.service.PropertyService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/view/all")
    @PreAuthorize("hasAnyRole('USER', 'HOST', 'ADMIN')")
    public ResponseEntity<@NonNull List<PropertyDto>> getAllProperties() {
        List<PropertyDto> properties = propertyService.getAllMyProperty();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAnyRole('USER', 'HOST', 'ADMIN')")
    public ResponseEntity<?> getPropertyById(@PathVariable("id") String id) {
        try {
            PropertyDto property = propertyService.findByPropertyId(id);
            return ResponseEntity.ok(property);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Property not found: " + e.getMessage());
        }
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProperty(@Valid @RequestBody PropertyDto propertyDto) {
        try {
            PropertyDto createdProperty = propertyService.createProperty(propertyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create property: " + e.getMessage());
        }
    }

    @PutMapping("/admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProperty(@PathVariable("id") String id,
                                            @Valid @RequestBody PropertyDto propertyDto) {
        try {
            PropertyDto updatedProperty = propertyService.updateProperty(id, propertyDto);
            return ResponseEntity.ok(updatedProperty);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to update property: " + e.getMessage());
        }
    }


    @DeleteMapping("/admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProperty(@PathVariable("id") String id) {
        try {
            propertyService.deleteMyProperty(id);
            return ResponseEntity.ok("Property deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to delete property: " + e.getMessage());
        }
    }
}