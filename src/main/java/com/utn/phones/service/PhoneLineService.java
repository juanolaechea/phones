package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.exceptions.*;
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


    public ResponseEntity<PhoneLine> addPhoneLine(PhoneLine phoneLine) {

        if(!phoneLineRepository.existsByNumberLine(phoneLine.getNumberLine())) {
            PhoneLine pl = phoneLineRepository.save(phoneLine);
            return ResponseEntity.ok(phoneLine);
        }else {
            throw new ElementExistsException();
        }


    }

    public List<PhoneLine> getAll() {
        return  phoneLineRepository.findAll();
    }

    public PhoneLine findByCode(Integer idPhoneLine) throws DeauthorizedException {
        return phoneLineRepository.findById(idPhoneLine)
                .orElseThrow(() -> new ElementDoesNotExistsException());
    }

    public void deletePhoneLine(Integer idPhoneLine) {
        try {
            this.phoneLineRepository.deleteById(idPhoneLine);
        }catch ( ElementDoesNotExistsException e){
            throw new ElementDoesNotExistsException();
        }
    }

    public PhoneLine getPhoneLineByNumberLine(String numberLine) {
        return this.phoneLineRepository.findByNumberLine(numberLine);

    }

    public void enablePhoneLine(Integer idPhoneLine) {
        try {
            PhoneLine pl= this.phoneLineRepository.getById(idPhoneLine);
            pl.setValid(true);
            this.phoneLineRepository.save(pl);
        }catch (ElementDoesNotExistsException e) {
            throw new ElementDoesNotExistsException();
        }
    }

    public void disablePhoneLine(Integer idPhoneLine) {
        try {
            PhoneLine pl= this.phoneLineRepository.getById(idPhoneLine);
            pl.setValid(false);
            this.phoneLineRepository.save(pl);
        }catch (ElementDoesNotExistsException e) {
            throw new ElementDoesNotExistsException();
        }
    }


}
