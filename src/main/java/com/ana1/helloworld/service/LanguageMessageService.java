package com.ana1.helloworld.service;

import com.ana1.helloworld.model.LanguageMessage;
import com.ana1.helloworld.repository.LanguageMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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



    //@PostConstruct //kad ovo ubacim izlazi greska
    public void run(ApplicationArguments args) {
        languageMessageRepository.save(new LanguageMessage(1, "Zdravo svete","Srpski"));
        languageMessageRepository.save(new LanguageMessage(2, "Ola mundo","Spanski"));
        languageMessageRepository.save(new LanguageMessage(3, "Bonjour monde","Francuski"));
        languageMessageRepository.save(new LanguageMessage(4, "Hallo welt","Nemacki"));
    }

    public List<LanguageMessage> findAll() {
        return languageMessageRepository.findAll();
    }

    public List<LanguageMessage> findByLanguage(@PathVariable String language) {return languageMessageRepository.findByLanguage(language);}

    public LanguageMessage getMessageById(int id) {
        return languageMessageRepository.findById(id).get();
    }

    public void saveOrUpdate(LanguageMessage languageMessage) {
        languageMessageRepository.save(languageMessage);
    }

    public void delete(int id) {
        languageMessageRepository.deleteById(id);
    }
}
