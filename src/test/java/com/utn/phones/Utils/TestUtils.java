package com.utn.phones.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.domain.*;
import com.utn.phones.dto.BillDto;
import com.utn.phones.dto.CallSenderDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


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
        return new Client(1,"juan","OLaecvhea",12345, UserType.valueOf("client"),new City(),new User(),new PhoneLine(),new ArrayList<Bill>(),new ArrayList<Call>());

    }

    public static String aEmployeeJson() {
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aEmployee());
    }

    public static Employee aEmployee() {
        return new Employee(1,"juan","OLaecvhea",12345, UserType.valueOf("client"),new User());

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

        return new CallSenderDto("222","222345678","06/05/2022 15:00:00",0x8000000000000000L);
    }

    public static String aCallJson(){
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aCall());
    }

    public static Call aCall(){
        return  new Call(1,new PhoneLine(),new PhoneLine(),LocalDateTime.of(2022,05,05,10,00,00)
                ,0x8000000000000000L,2.3F,3.4F,true,new City(),new City(),new Client());
    }

    public static String aTariffJson(){
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aTariff());
    }

    public static Tariff aTariff(){
        return  new Tariff(1, 4.5F,new City(),new City(),new ArrayList<Band>());
    }


    public static String aUserJson(){
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aUser());
    }

    public static User aUser(){
        return  new User(1,"juan","1234",UserType.client);
    }


    public static String aUserBand(){
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class,new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aBand());
    }

    public static Band aBand(){
        return  new Band(4,10,20,0.3F,new Tariff());
    }






}
