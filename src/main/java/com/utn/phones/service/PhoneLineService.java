package com.utn.phones.service;

import com.utn.phones.domain.PhoneLine;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.exceptions.ElementExistsException;
import com.utn.phones.exceptions.PhoneLineException;
import com.utn.phones.exceptions.ValidationPhoneLineException;
import com.utn.phones.persistence.PhoneLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;


@Service
public class PhoneLineService {

    private PhoneLineRepository phoneLineRepository;


    @Autowired
    public PhoneLineService(PhoneLineRepository phoneLineRepository) {
        this.phoneLineRepository = phoneLineRepository;
    }

    public PhoneLine addPhoneLine(PhoneLine phoneLine) {

        if(phoneLineRepository.findById(phoneLine.getIdLine()).isPresent()){
            throw new ElementExistsException();
        }
        return this.phoneLineRepository.save(phoneLine);
    }

    public PhoneLine findByCode(Integer idPhoneLine){
        return this.phoneLineRepository.findById(idPhoneLine).orElseThrow(ElementDoesNotExistsException::new);
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


    public List<PhoneLine> getAll(String number) {

        if(isNull(number)){
            return  phoneLineRepository.findAll();
        }else {

            return phoneLineRepository.findByNumberLine(number);
        }

    }
}
