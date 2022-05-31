package com.utn.phones.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class TestUtils {


    public static String aUserJson() {
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aUser());
    }

    public static User aUser() {
        return new User(1,"juan","OLaecvhea",12345,"1234",true, UserType.valueOf("client"),new City(),new PhoneLine());

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
        return new City(1,"Mar del plata","223",new State(),new ArrayList<User>());
    }

}
