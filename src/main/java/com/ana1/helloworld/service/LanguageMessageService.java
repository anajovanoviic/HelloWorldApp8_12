package com.ana1.helloworld.service;

import com.ana1.helloworld.exception.LanguageMessageNotFound;
import com.ana1.helloworld.model.LanguageMessage;
import com.ana1.helloworld.repository.LanguageMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;



import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LanguageMessageService implements ApplicationRunner{

    @Autowired
    LanguageMessageRepository languageMessageRepository;

    @Autowired
    public LanguageMessageService(LanguageMessageRepository languageMessageRepository) {
        this.languageMessageRepository = languageMessageRepository;
    }




    public void run(ApplicationArguments args) {
        languageMessageRepository.save(new LanguageMessage(1, "Zdravo svete","Srpski"));
        languageMessageRepository.save(new LanguageMessage(2, "Ola mundo","Spanski"));
        languageMessageRepository.save(new LanguageMessage(3, "Bonjour monde","Francuski"));
        languageMessageRepository.save(new LanguageMessage(4, "Hallo welt","Nemacki"));
        languageMessageRepository.save(new LanguageMessage(5, "Hello Wêreld","Afrikaans"));
        languageMessageRepository.save(new LanguageMessage(6, "Kaixo Mundua","Basque"));
        languageMessageRepository.save(new LanguageMessage(7, "Hola món","Catalan"));
        languageMessageRepository.save(new LanguageMessage(8, "Hej Verden","Danish"));
        languageMessageRepository.save(new LanguageMessage(9, "Sannu Duniya","Hausa"));
        languageMessageRepository.save(new LanguageMessage(10, "Helló Világ","Hungarian"));
    }

    public List<LanguageMessage> findAll() {
        return languageMessageRepository.findAll();
    }

    public List<LanguageMessage> findByLanguage(@PathVariable String language) {return languageMessageRepository.findByLanguage(language);}

    /*
    public LanguageMessage getMessageById(int id) {
        return languageMessageRepository.findById(id).get();
    }
    */



    public LanguageMessage getMessageById(int id) {
        return languageMessageRepository.findById(id).orElseThrow(()-> new LanguageMessageNotFound("Language message not found with id "+ id));
    }

    public void saveOrUpdate(LanguageMessage languageMessage) {
        languageMessageRepository.save(languageMessage);
    }

    public void delete(int id) {
        languageMessageRepository.deleteById(id);
    }
}
