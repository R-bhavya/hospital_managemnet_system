package com.patient_service.pat.services;

import com.patient_service.pat.entity.Patient;
import com.patient_service.pat.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient){
        return patientRepository.save(patient);
    }
    public List<Patient> getAllPatient(){

        return patientRepository.findAll();
    }
    public Optional<Patient> getPatientById(Long id){
        return patientRepository.findById(id);
    }
    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }
}
