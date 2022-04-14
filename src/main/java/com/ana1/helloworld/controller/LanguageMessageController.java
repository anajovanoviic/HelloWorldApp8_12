package com.ana1.helloworld.controller;

import com.ana1.helloworld.model.LanguageMessage;
import com.ana1.helloworld.service.LanguageMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller is an api where clients can submit requests

@RestController
public class LanguageMessageController {

    @Autowired
    LanguageMessageService languageMessageService;

    @GetMapping("/hello-rest")
    public String welcome() {

        return "Hello World";
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
        return languageMessageService.findAll();

    }


    @GetMapping("/translations/{id}")
    public LanguageMessage getMessage(@PathVariable("id") int id) {
        return languageMessageService.getMessageById(id);
    }

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
