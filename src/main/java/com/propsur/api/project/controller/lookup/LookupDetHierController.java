package com.propsur.api.project.controller.lookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propsur.api.project.dao.ApiResponse;
import com.propsur.api.project.entity.master.TmLookupDetHierarchical;
import com.propsur.api.project.service.master.LookupDetHierarchicalService;

@RestController
@RequestMapping("/api/masters/lookupdetheir")
public class LookupDetHierController {

    @Autowired
    private LookupDetHierarchicalService lookupDetHierarchicalService;

    // Get all LookupDetHierarchical entries
    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        try {
            List<TmLookupDetHierarchical> entries = lookupDetHierarchicalService.findAll();
            if (entries.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse("success", "No lookup hierarchical details found", null));
            }
            return ResponseEntity.ok(new ApiResponse("success", "Lookup hierarchical details fetched successfully", entries));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup hierarchical details: " + e.getMessage(), null));
        }
    }

    // Get LookupDetHierarchical entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Integer id) {
        try {
            TmLookupDetHierarchical entry = lookupDetHierarchicalService.findById(id);
            if (entry != null) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup hierarchical detail fetched successfully", entry));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "Lookup hierarchical detail not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup hierarchical detail: " + e.getMessage(), null));
        }
    }

    // Save a new LookupDetHierarchical entry
    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody TmLookupDetHierarchical tmLookupDetHierarchical) {
        try {
            TmLookupDetHierarchical savedEntry = lookupDetHierarchicalService.save(tmLookupDetHierarchical);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("success", "Lookup hierarchical detail created successfully", savedEntry));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error creating lookup hierarchical detail: " + e.getMessage(), null));
        }
    }

    // Update a LookupDetHierarchical entry
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody TmLookupDetHierarchical tmLookupDetHierarchical) {
        try {
            tmLookupDetHierarchical.setLookupDetHierId(id); // Ensure the ID is set before updating
            TmLookupDetHierarchical updatedEntry = lookupDetHierarchicalService.save(tmLookupDetHierarchical);
            return ResponseEntity.ok(new ApiResponse("success", "Lookup hierarchical detail updated successfully", updatedEntry));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error updating lookup hierarchical detail: " + e.getMessage(), null));
        }
    }

    // Delete a LookupDetHierarchical entry
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        try {
            lookupDetHierarchicalService.delete(id);
            return ResponseEntity.ok(new ApiResponse("success", "Lookup hierarchical detail deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error deleting lookup hierarchical detail: " + e.getMessage(), null));
        }
    }
}
