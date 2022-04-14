package com.ana1.helloworld.repository;

import com.ana1.helloworld.model.LanguageMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageMessageRepository extends CrudRepository<LanguageMessage, Integer> {

    List<LanguageMessage> findAll();
    List<LanguageMessage> findByLanguage(String language);

    //void saveOrUpdate(LanguageMessage languageMessage); // ovo ne smes da stavljas, izaiva gresku

}
