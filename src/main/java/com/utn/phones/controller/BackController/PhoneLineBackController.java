package com.utn.phones.controller.BackController;

import com.utn.phones.domain.PhoneLine;
import com.utn.phones.exceptions.PhoneLineException;
import com.utn.phones.exceptions.ValidationPhoneLineException;
import com.utn.phones.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.BASE_URL;
import static com.utn.phones.constants.ControllerConstants.URL_PHONE_LINE;

@RestController
@RequestMapping(BASE_URL)
public class PhoneLineBackController {


    private PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineBackController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    //Agregar linea
    @PostMapping(URL_PHONE_LINE)
    public ResponseEntity<PhoneLine> addPhoneLine(@RequestBody PhoneLine phoneLine, HttpServletRequest request) {
        PhoneLine p = this.phoneLineService.addPhoneLine(phoneLine);
        URI location = ServletUriComponentsBuilder.fromServletMapping(request).path("/api/phoneLine/" + p.getIdLine()).build().toUri();
        return ResponseEntity.created(location).build();
    }
    //Buscar linea por id
    @GetMapping(path = URL_PHONE_LINE + "/{idPhoneLine}")
    public ResponseEntity<PhoneLine> getPhoneLineById(@PathVariable("idPhoneLine")Integer idPhoneLine){
        return ResponseEntity.ok(this.phoneLineService.findByCode(idPhoneLine));
    }
    //eliminar linea
    @DeleteMapping(path = URL_PHONE_LINE + "/{idPhoneLine}")
    public void deletePhoneLine(@PathVariable("idPhoneLine") Integer idPhoneLine){
        this.phoneLineService.deletePhoneLine(idPhoneLine);
    }
    //Habilitar linea
    @PutMapping(path = URL_PHONE_LINE + "/enable/{idPhoneLine}")
    public void enablePhoneLine (@PathVariable("idPhoneLine") Integer idPhoneLine)throws ValidationPhoneLineException, PhoneLineException {
        try{
            this.phoneLineService.enablePhoneLine(idPhoneLine);
        }catch (JpaSystemException e){
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }

    //Deshabilitar linea
    @PutMapping(path = URL_PHONE_LINE + "/disable/{idPhoneLine}")
    public void disablePhoneLine (@PathVariable("isPhoneLine")Integer idPhoneLine) throws ValidationPhoneLineException, PhoneLineException {
        try {
            this.phoneLineService.disablePhoneLine(idPhoneLine);
        }catch(JpaSystemException e){
            throw  new PhoneLineException((e.getCause().getCause().getMessage()));
        }
    }

    @GetMapping(path = URL_PHONE_LINE +"/")
    public List<PhoneLine> getAll(@RequestParam(required = false) String number )  {
        return phoneLineService.getAll(number);
    }






}
