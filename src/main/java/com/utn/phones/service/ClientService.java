package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.*;
import com.utn.phones.dto.BillDto;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.ElementDoesNotAClient;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utn.phones.Utils.EntityURLBuilder.buildURL;
import static com.utn.phones.constants.ControllerConstants.URL_CLIENT;
import static com.utn.phones.constants.ControllerConstants.URl_USER;

@Service
public class ClientService {



    private final ClientRepository clientRepository;
    private final CityRepository cityRepository;
    private final PhoneLineRepository phoneLineRepository;
    private final CallRepository callRepository;
    private final BillRepository billRepository;
    private final UserService userService;

    @Autowired
    public ClientService(ClientRepository clientRepository, CityRepository cityRepository, PhoneLineRepository phoneLineRepository, CallRepository callRepository, BillRepository billRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.cityRepository = cityRepository;
        this.phoneLineRepository = phoneLineRepository;
        this.callRepository = callRepository;
        this.billRepository = billRepository;
        this.userService = userService;
    }

    //preguntar si es un cliente
    public PostResponse addClient(Client client) throws ElementDoesNotAClient {
            User u = new User();
            u.setUsername(client.getName().toLowerCase());
            u.setPassword("a");
            u.setUserType(client.getUserType());
            userService.addUser(u);
            Client c= client;
            c.setUser(u);
            this.clientRepository.save(c);

            return PostResponse.builder()
                    .httpStatus(HttpStatus.CREATED)
                    .link(buildURL(URL_CLIENT, c.getIdClient().toString()))
                    .build();

    }

    public List<ClientDto> findAllClient() {
        List<Client> clients =this.clientRepository.findAll();
        List<ClientDto>clientDtos= new ArrayList<>();
        for (Client client1 : clients) {

            ClientDto cdto = ClientDto.to(client1);
            clientDtos.add(cdto);

        }
        return clientDtos;

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
                .link(buildURL(URL_CLIENT, cl.getCity().getName()))
                .build();
    }
    public PostResponse putPhoneLineInUser(Integer idClient, Integer idPhoneLine) {
        Client cl = this.clientRepository.getById(idClient);
        PhoneLine p = this.phoneLineRepository.getById(idPhoneLine);
        City c= this.cityRepository.getCodeByNumber(p.getNumberLine());
        cl.setPhoneLine(p);
        cl.setCity(c);
        this.clientRepository.save(cl);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(URL_CLIENT, cl.getPhoneLine().getNumberLine()))
                .build();
    }



    public void deleteClient(Integer idClient) throws ElementDoesNotExistsException {
       try {
            this.clientRepository.deleteById(idClient);
       }catch ( ElementDoesNotExistsException e){
           throw new ElementDoesNotExistsException();
       }
    }


    public Client getClientByNumber(PhoneLine phoneLine) {
        return this.clientRepository.findByPhoneLine(phoneLine);
    }

    public List<Call> getClientByRank(Integer idClient, LocalDate from, LocalDate to) {
        return this.callRepository.findAllByClientBetweenDates(idClient,from,to);
    }

    public List<BillDto> getBillsByRank(Integer idClient, LocalDate from, LocalDate to) {

        List<Bill> bills=this.billRepository.findAllByClientBetweenDates(idClient,from,to);
        List<BillDto>billDtos=new ArrayList<>();
        for (Bill bill1 : bills) {
            BillDto billDto = BillDto.to(bill1);
            billDtos.add(billDto);
        }
        return billDtos;
    }

    public PostResponse putUserInClient(Integer idClient, Integer idUser) {
        Client cl = this.clientRepository.getById(idClient);
        cl.setUser(this.userService.findByCode(idUser));
        this.clientRepository.save(cl);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(URl_USER, cl.getUser().getUsername()))
                .build();
    }
}
