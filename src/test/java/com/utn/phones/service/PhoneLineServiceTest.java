package com.utn.phones.service;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.Utils.TestUtils;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.PhoneLineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utn.phones.Utils.TestUtils.aClient;
import static com.utn.phones.Utils.TestUtils.aPhoneLine;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PhoneLineServiceTest {


    @InjectMocks
    private PhoneLineService phoneLineService;
    @Mock
    private PhoneLineRepository phoneLineRepository;

    @Test
    public void addPhoneLine() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final PhoneLine aPhoneLine=aPhoneLine();
        final PhoneLine aPhoneLineSaved =aPhoneLine();
        aPhoneLineSaved.setIdLine(1);

        Mockito.when(phoneLineRepository.save(aPhoneLine)).thenReturn(aPhoneLineSaved);

        final PostResponse response = phoneLineService.addPhoneLine(aPhoneLine);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void getAll(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<PhoneLine>phoneLines= new ArrayList<>();
        phoneLines.add(aPhoneLine());

        final List<PhoneLine> response = phoneLineService.getAll();
        assertNotNull(response);
    }
    @Test
    public void findByCode(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Integer id= 1;
        Mockito.when(phoneLineRepository.findById(id)).thenReturn(Optional.of(aPhoneLine()));

        final PhoneLine response = phoneLineService.findByCode(id);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void findByCodeBadRequest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Integer id= null;
        Mockito.when(phoneLineRepository.findById(id)).thenReturn(Optional.of(aPhoneLine()));

        final HttpStatus response = HttpStatus.BAD_REQUEST;
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void deletePhoneLine()throws Exception{
        doNothing().when(phoneLineRepository).deleteById(anyInt());

        assertDoesNotThrow(() -> phoneLineService.deletePhoneLine(1));

        verify(phoneLineRepository, times(1)).deleteById(anyInt());
    }
    @Test
    public void deletePhoneLineThrows()throws Exception{
        doThrow(ElementDoesNotExistsException.class).when(phoneLineRepository).deleteById(anyInt());

        assertThrows(ElementDoesNotExistsException.class,() -> phoneLineService.deletePhoneLine(1));

        verify(phoneLineRepository, times(1)).deleteById(anyInt());
    }
    @Test
    public void getPhoneLineByNumberLine(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String number="2236688022";
        Mockito.when(phoneLineRepository.findByNumberLine(number)).thenReturn(aPhoneLine());

        final PhoneLine response = phoneLineService.getPhoneLineByNumberLine(number);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void enablePhoneLine()throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(phoneLineRepository.getById(aPhoneLine().getIdLine())).thenReturn(aPhoneLine());
        assertDoesNotThrow(() -> phoneLineService.enablePhoneLine(aPhoneLine().getIdLine()));

    }
    @Test
    public void enablePhoneLineThrow()throws Exception{
        doThrow(ElementDoesNotExistsException.class).when(phoneLineRepository).getById(aPhoneLine().getIdLine());

        assertThrows(ElementDoesNotExistsException.class,() -> phoneLineService.enablePhoneLine(aPhoneLine().getIdLine()));

    }
    @Test
    public void disablePhoneLine()throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Mockito.when(phoneLineRepository.getById(aPhoneLine().getIdLine())).thenReturn(aPhoneLine());
        assertDoesNotThrow(() -> phoneLineService.disablePhoneLine(aPhoneLine().getIdLine()));

    }
    @Test
    public void disablePhoneLineThrow()throws Exception{
        doThrow(ElementDoesNotExistsException.class).when(phoneLineRepository).getById(aPhoneLine().getIdLine());

        assertThrows(ElementDoesNotExistsException.class,() -> phoneLineService.disablePhoneLine(aPhoneLine().getIdLine()));

    }

}
