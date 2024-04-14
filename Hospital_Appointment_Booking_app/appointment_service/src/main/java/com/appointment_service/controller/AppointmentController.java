package com.appointment_service.controller;
import com.appointment_service.entity.Appointment;
import com.appointment_service.payload.Doctor;
import com.appointment_service.payload.Patient;
import com.appointment_service.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    public AppointmentController(RestTemplate restTemplate, AppointmentService appointmentService) {
        this.restTemplate = restTemplate;
        this.appointmentService = appointmentService;
       }

    public Appointment saveAppointment(@RequestBody Appointment appointment){
        return appointmentService.saveAppointment(appointment);
    }
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        ResponseEntity<Patient> patientResponse = restTemplate.getForEntity("http://patient-service/patients/" + appointment.getPatientId(), Patient.class);


        if (patientResponse.getStatusCode() != HttpStatus.OK || patientResponse.getBody() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Patient patient = patientResponse.getBody();

        ResponseEntity<Doctor> doctorResponse = restTemplate.getForEntity("http://doctor-service/doctors/"+appointment.getDoctorId(),Doctor.class);

        if(doctorResponse.getStatusCode() != HttpStatus.OK || patientResponse.getBody() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Doctor doctor = doctorResponse.getBody();

        Appointment newAppointment = appointmentService.saveAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }
}
