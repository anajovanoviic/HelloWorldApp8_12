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
    private LanguageMessageService languageMessageService;

    /*
    @GetMapping("/welcomeForTest")
    public String welcomeForTest() {
        return "Welcome";
    }
*/

    public LanguageMessageController(LanguageMessageService languageMessageService) {
        this.languageMessageService = languageMessageService;
    }


    @GetMapping("/welcomeForTest")
    public String welcomeForTest(@RequestParam(defaultValue = "Stranger") String name) {
        return languageMessageService.getWelcomeMessage(name);

    }


/*
    public void boolean1(boolean param) {
        if(param == false) {
            throw new IllegalArgumentException("param cannot be false");

        }
        else {
            System.out.println("x = 5" );
        }
    }

 */

    @GetMapping("/helloTEST")
    public String helloTEST(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s", name);
    }
/*
    public String hello(String name) {
        return String.format("Hello, %s", name);
    }

*/

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
        //throw new ApiRequestException("Oops cannot get all students with custom exception");
        //throw new IllegalStateException("Oops cannot get all language messages");
        return languageMessageService.findAll();

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
