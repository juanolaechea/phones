package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.exceptions.ElementExistsException;
import com.utn.phones.exceptions.PhoneLineException;
import com.utn.phones.exceptions.ValidationPhoneLineException;
import com.utn.phones.persistence.PhoneLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static com.utn.phones.Utils.EntityURLBuilder.buildURL;

import static java.util.Objects.isNull;


@Service
public class PhoneLineService {

    private static final String currentPath = "phoneLine";
    private PhoneLineRepository phoneLineRepository;


    @Autowired
    public PhoneLineService(PhoneLineRepository phoneLineRepository) {
        this.phoneLineRepository = phoneLineRepository;
    }

    public PostResponse addPhoneLine(PhoneLine phoneLine) {
        PhoneLine pl =phoneLineRepository.save(phoneLine);

        return PostResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .link(buildURL(currentPath, pl.getIdLine().toString()))
                .build();

    }

    public List<PhoneLine> getAll() {
        return  phoneLineRepository.findAll();
    }

    public PhoneLine findByCode(Integer idPhoneLine){
        return phoneLineRepository.findById(idPhoneLine)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not Exists "));
    }

    public void deletePhoneLine(Integer idPhoneLine) {
        this.phoneLineRepository.deleteById(idPhoneLine);
    }

    public void enablePhoneLine(Integer idPhoneLine) {

        if(idPhoneLine != null && idPhoneLine >0){
            PhoneLine pl= this.phoneLineRepository.getById(idPhoneLine);
            pl.setValid(true);
            this.phoneLineRepository.save(pl);
        }else {
            throw new ValidationPhoneLineException();
        }
    }

    public void disablePhoneLine(Integer idPhoneLine) {
        if(idPhoneLine != null && idPhoneLine ==0){
            PhoneLine pl= this.phoneLineRepository.getById(idPhoneLine);
            pl.setValid(false);
            this.phoneLineRepository.save(pl);
        }else {
            throw new ValidationPhoneLineException();
        }
    }

    public PhoneLine getPhoneLineByNumberLine(String numberLine)throws ElementDoesNotExistsException {

       if(numberLine != null){
           return this.phoneLineRepository.findByNumberLine(numberLine);
       }else {
           throw new ElementDoesNotExistsException();
       }
    }
}
