package com.utn.phones.service;


import com.utn.phones.domain.Band;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.persistence.BandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.aBand;
import static com.utn.phones.Utils.TestUtils.aTariff;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BandServiceTest {

    @InjectMocks
    BandService bandService;

    @Mock
    BandRepository bandRepository;











}

/*
    public  Float getAllBandByTariff(Integer idTariff,Integer hourIni){
        Float response= Float.valueOf(0);
        List<Band> brands = bandRepository.findAllByTariff(idTariff);
        for (Band brand : brands) {
            response=getPriceByHour(brand.getIdBand(),hourIni);
        }
        return response;

    }

 */

