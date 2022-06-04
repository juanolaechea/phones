package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.UserType;
import com.utn.phones.exceptions.ElementDoesNotAClient;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.exceptions.ValidationClientException;
import com.utn.phones.persistence.CityRepository;
import com.utn.phones.persistence.ClientRepository;
import com.utn.phones.persistence.PhoneLineRepository;
import com.utn.phones.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.EntityURLBuilder.buildURL;

@Service
public class ClientService {

    private static final String currentPath = "user";

    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;
    private final PhoneLineRepository phoneLineRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository, CityRepository cityRepository, PhoneLineRepository phoneLineRepository) {
        this.clientRepository = clientRepository;
        this.cityRepository = cityRepository;
        this.phoneLineRepository = phoneLineRepository;
    }

    //preguntar si es un cliente
    public PostResponse addClient(Client client) throws ElementDoesNotAClient {

        if(client.getUserType() == UserType.client){
            Client cl = clientRepository.save(client);
            return PostResponse.builder()
                    .httpStatus(HttpStatus.CREATED)
                    .link(buildURL(currentPath, cl.getIdClient().toString()))
                    .build();
        }else {
            throw new ElementDoesNotAClient();
        }
    }

    public List<Client> findAllClient() {
        return this.clientRepository.findAll();
    }

    public Client findByCode(Integer idClient)throws ElementDoesNotExistsException {
        return this.clientRepository.findById(idClient)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Client not Exists "));
    }

    public PostResponse putCityInUser(Integer idClient, Integer idCity) {
        Client cl= this.clientRepository.getById(idClient);
        City c= this.cityRepository.getById(idCity);
        cl.setCity(c);
        this.clientRepository.save(cl);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(currentPath, cl.getCity().getName()))
                .build();
    }
    public PostResponse putPhoneLineInUser(Integer idClient, Integer idPhoneLine) {
        Client cl = this.clientRepository.getById(idClient);
        PhoneLine p = this.phoneLineRepository.getById(idPhoneLine);
        cl.setPhoneLine(p);
        this.clientRepository.save(cl);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(currentPath, cl.getPhoneLine().getNumberLine()))
                .build();
    }



    public PostResponse deleteClient(Integer idClient) {

        this.clientRepository.deleteById(idClient);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .build();
    }


    public Client getClientByNumber(PhoneLine phoneLine) {
        return this.clientRepository.findByPhoneLine(phoneLine);
    }
}
