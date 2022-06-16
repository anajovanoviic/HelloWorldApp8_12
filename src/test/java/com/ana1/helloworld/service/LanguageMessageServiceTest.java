package com.ana1.helloworld.service;

import com.ana1.helloworld.repository.LanguageMessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

//import static com.sun.javaws.JnlpxArgs.verify;


class LanguageMessageServiceTest {



    @Mock
    private LanguageMessageRepository languageMessageRepository;
    private LanguageMessageService underTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new LanguageMessageService(languageMessageRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canFindAll() {

        //when
        underTest.findAll();
        //then
        verify(languageMessageRepository).findAll();
    }

    @Test
    @Disabled
    void findByLanguage() {
    }

    @Test
    @Disabled
    void getMessageById() {
    }

    @Test
    @Disabled
    void saveOrUpdate() {
    }

    @Test
    @Disabled
    void delete() {
    }
}