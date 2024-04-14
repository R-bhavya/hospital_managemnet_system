package com.patient_service.pat.controller;

import com.patient_service.pat.entity.Patient;
import com.patient_service.pat.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatient();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable("id") Long id){

        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        patientService.deletePatient(id);
    }

}
