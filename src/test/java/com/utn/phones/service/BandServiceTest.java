package com.utn.phones.service;


import com.utn.phones.persistence.BandRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BandServiceTest {

    @InjectMocks
    BandService bandService;

    @Mock
    BandRepository bandRepository;



}
