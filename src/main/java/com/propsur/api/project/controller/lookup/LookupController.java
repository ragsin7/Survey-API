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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.propsur.api.project.dao.ApiResponse;
import com.propsur.api.project.entity.master.TmLookup;
import com.propsur.api.project.service.master.LookupService;
import com.propsur.api.project.service.master.bean.LookupBean;

@RestController
@RequestMapping("/api/masters/lookup")
public class LookupController {

    @Autowired
    private LookupService lookupService;

    @GetMapping("/{lookupId}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getLookupById(@PathVariable Integer lookupId) {
        try {
            LookupBean lookup = lookupService.findById(lookupId);
            if (lookup != null) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup found successfully", lookup));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "Lookup not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup: " + e.getMessage(), null));
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> getAllLookups() {
        try {
            List<LookupBean> lookups = lookupService.findAll();
            if (lookups.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse("success", "No lookups found", null));
            } else {
                return ResponseEntity.ok(new ApiResponse("success", "Lookups fetched successfully", lookups));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookups: " + e.getMessage(), null));
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> createLookup(@RequestBody TmLookup tmLookup) {
        try {
            TmLookup savedLookup = lookupService.save(tmLookup);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("success", "Lookup created successfully", savedLookup));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error creating lookup: " + e.getMessage(), null));
        }
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> updateLookup(@RequestBody TmLookup tmLookup) {
        try {
            lookupService.updateTmLookup(tmLookup);
            return ResponseEntity.ok(new ApiResponse("success", "Lookup updated successfully", tmLookup));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error updating lookup: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{lookupId}")
    @ResponseBody
    public ResponseEntity<ApiResponse> deleteLookup(@PathVariable Integer lookupId) {
        try {
            lookupService.delete(lookupId);
            return ResponseEntity.ok(new ApiResponse("success", "Lookup deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error deleting lookup: " + e.getMessage(), null));
        }
    }

    @GetMapping("/code/{lookupCode}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getLookupByCode(@PathVariable String lookupCode) {
        try {
            TmLookup lookup = lookupService.findByLookupCode(lookupCode);
            if (lookup != null) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup found successfully", lookup));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "Lookup not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup: " + e.getMessage(), null));
        }
    }
}
