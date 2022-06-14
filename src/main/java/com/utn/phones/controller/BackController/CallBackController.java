package com.utn.phones.controller.BackController;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Call;
import com.utn.phones.dto.CallSenderDto;
import com.utn.phones.service.CallService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.utn.phones.constants.ControllerConstants.BASE_URL;
import static com.utn.phones.constants.ControllerConstants.URL_CALL;
@Controller
@RestController
@RequestMapping(BASE_URL)
@Slf4j
public class CallBackController {

    private CallService callService;

    public CallBackController(CallService callService) {
        this.callService = callService;
    }

    @PostMapping(URL_CALL )//testok
    public ResponseEntity newCall(@RequestBody CallSenderDto callSenderDto){
        this.callService.newCall(CallSenderDto.to(callSenderDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
