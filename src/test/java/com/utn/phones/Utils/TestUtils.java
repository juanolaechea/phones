package com.utn.phones.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.domain.*;

import java.time.LocalDate;
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
        return new Client(1,"juan","OLaecvhea",12345, UserType.valueOf("client"),new City(),new User(),new PhoneLine());

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

}
