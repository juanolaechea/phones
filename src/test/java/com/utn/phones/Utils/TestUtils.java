package com.utn.phones.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.domain.*;
import com.utn.phones.dto.CallSenderDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


public class TestUtils {


    public static String aClientJson() {
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aClient());
    }

    public static Client aClient() {
        return new Client(1,"juan","OLaecvhea",12345, UserType.valueOf("client"),new City(),new User(),new PhoneLine(),new ArrayList<Bill>());

    }

    public static String aCityJson(){
        final Gson prettyGson= new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aCity());
    }

    public static City aCity(){
        return new City(1,"Mar del plata","223",new State(),new ArrayList<Client>());
    }


    public static String aPhoneJson(){
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aPhoneLine());
    }

    public static PhoneLine aPhoneLine() {

        return new PhoneLine(1,"222345678",true,PhoneLineType.valueOf("home"),new ArrayList<Bill>());
    }

    public static String aCallDtoJson(){
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aCallDto());
    }

    public static CallSenderDto aCallDto() {

        return new CallSenderDto("222","222345678","06/02/2018 15:00:00",0x8000000000000000L);
    }








}
