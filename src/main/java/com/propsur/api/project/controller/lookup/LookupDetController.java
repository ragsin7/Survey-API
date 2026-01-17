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
import com.propsur.api.project.entity.master.TmLookupDet;
import com.propsur.api.project.service.master.LookupDetService;
import com.propsur.api.project.service.master.bean.LookupDetBean;

@RestController
@RequestMapping("/api/masters/lookupdet")
public class LookupDetController {

    @Autowired
    private LookupDetService lookupDetService;

    @GetMapping("/{lookupDetId}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getLookupDetById(@PathVariable Integer lookupDetId) {
        try {
            TmLookupDet lookupDet = lookupDetService.findById(lookupDetId);
            if (lookupDet != null) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup detail fetched successfully", lookupDet));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "Lookup detail not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup detail: " + e.getMessage(), null));
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> getAllLookupDets() {
        try {
            List<TmLookupDet> lookupDets = lookupDetService.findAll();
            if (lookupDets.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse("success", "No lookup details found", null));
            } else {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup details fetched successfully", lookupDets));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup details: " + e.getMessage(), null));
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> createLookupDet(@RequestBody List<LookupDetBean> lookupdetbean) {
        try {
        	List<LookupDetBean> lookupdet = lookupDetService.save(lookupdetbean);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("success", "Lookup detail created successfully", lookupdet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error creating lookup detail: " + e.getMessage(), null));
        }
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> updateLookupDet(@RequestBody TmLookupDet tmLookupDet) {
        try {
            lookupDetService.updateTmLookupDet(tmLookupDet);
            return ResponseEntity.ok(new ApiResponse("success", "Lookup detail updated successfully", tmLookupDet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error updating lookup detail: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{lookupDetId}")
    @ResponseBody
    public ResponseEntity<ApiResponse> deleteLookupDet(@PathVariable Integer lookupDetId) {
        try {
            lookupDetService.delete(lookupDetId);
            return ResponseEntity.ok(new ApiResponse("success", "Lookup detail deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error deleting lookup detail: " + e.getMessage(), null));
        }
    }

    @GetMapping("/code/{lookupCode}/{lookupDetCode}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getLookupDetByCode(@PathVariable String lookupCode,@PathVariable String lookupDetCode) {
        try {
            TmLookupDet lookupDet = lookupDetService.findByLookupDetCode(lookupCode,lookupDetCode);
            if (lookupDet != null) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup detail fetched successfully", lookupDet));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "Lookup detail not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup detail: " + e.getMessage(), null));
        }
    }

    @GetMapping("/code-dis/{lookupCode}/{lookupDetHierIdDistrict}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getLookupDetByLkpCodeDistirctId(@PathVariable String lookupCode,
    		@PathVariable Integer lookupDetHierIdDistrict) {
        try {
            List<LookupDetBean> lookupDet = lookupDetService.getLookupDetByLkpCodeDistirctId(lookupCode,lookupDetHierIdDistrict);
            if (!lookupDet.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup details fetched successfully", lookupDet));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "No lookup details found for the given code", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup details: " + e.getMessage(), null));
        }
    
    }
    
    @GetMapping("/code/{lookupCode}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getLookupDetByLkpCode(@PathVariable String lookupCode) {
        try {
            List<LookupDetBean> lookupDet = lookupDetService.findByLookupDetLkpCode(lookupCode);
            if (!lookupDet.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse("success", "Lookup details fetched successfully", lookupDet));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("fail", "No lookup details found for the given code", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("fail", "Error fetching lookup details: " + e.getMessage(), null));
        }
    }
}
