package com.utn.phones.controller.BackController;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.exceptions.*;
import com.utn.phones.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.net.Authenticator;
import java.net.URI;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.BASE_URL;
import static com.utn.phones.constants.ControllerConstants.URL_PHONE_LINE;

@Controller
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
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse addPhoneLine(@RequestBody PhoneLine phoneLine) {
        return phoneLineService.addPhoneLine(phoneLine);
    }
    //Traer todas las lineas
    @GetMapping(path = URL_PHONE_LINE +"/")
    public  ResponseEntity<List<PhoneLine> > getAll( )  {
        List<PhoneLine>phoneLines = this.phoneLineService.getAll();
        return phoneLines.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(phoneLines);
    }

    //Buscar linea por id
    @GetMapping(path = URL_PHONE_LINE + "/{idPhoneLine}")
    public PhoneLine getPhoneLineById(@PathVariable("idPhoneLine") Integer idPhoneLine) {
        return this.phoneLineService.findByCode(idPhoneLine);
    }

    //Eliminar linea
    @DeleteMapping(path = URL_PHONE_LINE + "/{idPhoneLine}")
    public void deletePhoneLine(@PathVariable("idPhoneLine") Integer idPhoneLine) throws ElementDoesNotExistsException{
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







}
