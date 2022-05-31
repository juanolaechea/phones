package com.utn.phones.controller;

import com.utn.phones.controller.BackController.CallBackController;
import com.utn.phones.dto.CallSenderDto;
import com.utn.phones.service.CallService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.utn.phones.constants.ControllerConstants.URL_CALL;

@SpringBootTest(classes = CallBackController.class)
public class CallBackControllTest extends Abstrascttest{


    @MockBean
    CallService callService;




    /*

    @PostMapping(URL_CALL )
    public ResponseEntity newCall(@RequestBody CallSenderDto callSenderDto){
        this.callService.newCall(CallSenderDto.to(callSenderDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

     */


}
