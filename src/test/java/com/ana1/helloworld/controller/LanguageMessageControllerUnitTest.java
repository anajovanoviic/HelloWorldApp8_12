package com.ana1.helloworld.controller;

import com.ana1.helloworld.advice.LoggingAdvice;
import com.ana1.helloworld.service.LanguageMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LanguageMessageControllerUnitTest {



    //unit test
    @Test
    void shouldWelcomeForTest() {


        LanguageMessageService languageMessageService = Mockito.mock(LanguageMessageService.class);
        when(languageMessageService.getWelcomeMessage("John")).thenReturn("Welcome John!");
        LanguageMessageController languageMessageController = new LanguageMessageController(languageMessageService);
        assertEquals("Welcome John!", languageMessageController.welcomeForTest("John"));
    }
/*
    @Test
    void trueShouldReturnFive() {

        LanguageMessageController object = new LanguageMessageController();
        //this piece of code verifies that when this code is executed grader.determineLetterGrade(-1); it throws an illegal exception
        assertThrows(IllegalArgumentException.class,
                () -> {
                    object.boolean1(true);
                });

    }
*/

}