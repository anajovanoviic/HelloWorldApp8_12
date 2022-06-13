package com.ana1.helloworld.controller;

import com.ana1.helloworld.exception.ApiRequestException;
import com.ana1.helloworld.exception.CustomException;
import com.ana1.helloworld.model.LanguageMessage;
import com.ana1.helloworld.service.LanguageMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//Controller is an api where clients can submit requests

@RestController
public class LanguageMessageController {

    @Autowired
    LanguageMessageService languageMessageService;

    @GetMapping("/hello-rest")
    public String welcome() {

        return "Hello World";
    }



    @GetMapping("/countries")
    public  List<Object> getCountries() {
        String url = "https://restcountries.com/v3.1/all";
        RestTemplate restTemplate = new RestTemplate();

        Object[] countries = restTemplate.getForObject(url, Object[].class);

        return Arrays.asList(countries);
    }




    @GetMapping("/hello.html")
    public String hello() {

        return "Hello World2";
    }

    @GetMapping("hello/{language}")
    public List<LanguageMessage> getTranslationByLang(@PathVariable String language) {
        return languageMessageService.findByLanguage(language);
    }

    @GetMapping("secure/hello")
    public String secureHello() {

        return "Secure Hello";
    }

    @GetMapping("/admin.html")
    public String admin() {

        return "Admin page";
    }

    @PostMapping("/adminPage")
    public int addLanguageMessage(@RequestBody LanguageMessage languageMessage) {
        languageMessageService.saveOrUpdate(languageMessage);
        return languageMessage.getId();
    }

    @GetMapping("/translations")
    public List<LanguageMessage> getAllTranslations() {
        throw new ApiRequestException("Oops cannot get all students with custom exception");
        //throw new IllegalStateException("Oops cannot get all language messages");
        //return languageMessageService.findAll();

    }

/*
    @GetMapping("/translations/{id}")
    public LanguageMessage getMessage(@PathVariable("id") int id) {
        return languageMessageService.getMessageById(id);
    }
*/

    @GetMapping("/translations/{id}")
    public LanguageMessage getMessage(@PathVariable("id") int id) {
        return languageMessageService.getMessageById(id);
    }

   /* @RequestMapping("/translations/{id}")
    public LanguageMessage getMessage(@PathVariable("id") int id) {
        return languageMessageService.getMessageById(id);
    }*/

    @DeleteMapping("/translations/{id}")
    public void deleteMessage(@PathVariable("id") int id) {
        languageMessageService.delete(id);
    }

    @PostMapping("/translations")
    public int saveMessage(@RequestBody LanguageMessage languageMessage) {
        languageMessageService.saveOrUpdate(languageMessage);
        return languageMessage.getId();
    }


}
