package com.ana1.helloworld.repository;

import com.ana1.helloworld.advice.LoggingAdvice;
import com.ana1.helloworld.model.LanguageMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThat;
//import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DataJpaTest
class LanguageMessageRepositoryTest {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);


    @Autowired
    private LanguageMessageRepository underTest;

    @Test
    void shouldFindAll() {
    }



    @Test
    void shouldFindByLanguage() {

        log.info("Class started");

        //given
        String srpski = "Srpski";

        LanguageMessage languageMessage = new LanguageMessage(
                1, "Zdravo svete", srpski
        );

        /*List<LanguageMessage> list = Arrays.asList(
                new LanguageMessage(1, "Zdravo svete", srpski)

        );*/

        //LanguageMessage languageMessage = LanguageMessage.builder()

        underTest.save(languageMessage);


        //when
        List<LanguageMessage> expected = underTest.findByLanguage(srpski);

        //then
        //assertThat(expected, contains(1, "Zdravo svete", srpski));


        assertThat(expected, containsInAnyOrder(
                new LanguageMessage(1, "Zdravo svete", srpski)

        ));
    }


}