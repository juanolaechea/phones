package com.utn.phones.service;

import com.utn.phones.persistence.CityRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityServiceTest {

    @InjectMocks
    CityService cityService;

    @Mock
    CityRepository cityRepository;


}
