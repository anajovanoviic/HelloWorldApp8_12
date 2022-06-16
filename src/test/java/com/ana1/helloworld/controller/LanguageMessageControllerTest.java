package com.ana1.helloworld.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.ana1.helloworld.model.LanguageMessage;
import com.ana1.helloworld.service.LanguageMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LanguageMessageController.class})
@ExtendWith(SpringExtension.class)
class LanguageMessageControllerTest {
    @Autowired
    private LanguageMessageController languageMessageController;

    @MockBean
    private LanguageMessageService languageMessageService;

    /**
     * Method under test: {@link LanguageMessageController#addLanguageMessage(LanguageMessage)}
     */
    @Test
    void testAddLanguageMessage() throws Exception {
        doNothing().when(this.languageMessageService).saveOrUpdate((LanguageMessage) any());

        LanguageMessage languageMessage = new LanguageMessage();
        languageMessage.setId(1);
        languageMessage.setLanguage("en");
        languageMessage.setTranslation("Translation");
        String content = (new ObjectMapper()).writeValueAsString(languageMessage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adminPage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link LanguageMessageController#admin()}
     */
    @Test
    void testAdmin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin.html");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin page"));
    }

    /**
     * Method under test: {@link LanguageMessageController#admin()}
     */
    @Test
    void testAdmin2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/admin.html");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin page"));
    }

    /**
     * Method under test: {@link LanguageMessageController#deleteMessage(int)}
     */
    @Test
    void testDeleteMessage() throws Exception {
        doNothing().when(this.languageMessageService).delete(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/translations/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link LanguageMessageController#deleteMessage(int)}
     */
    @Test
    void testDeleteMessage2() throws Exception {
        doNothing().when(this.languageMessageService).delete(anyInt());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/translations/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link LanguageMessageController#getAllTranslations()}
     */
    @Test
    void testGetAllTranslations() throws Exception {
        when(this.languageMessageService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/translations");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LanguageMessageController#getAllTranslations()}
     */
    @Test
    void testGetAllTranslations2() throws Exception {
        when(this.languageMessageService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/translations");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LanguageMessageController#getMessage(int)}
     */
    @Test
    void testGetMessage() throws Exception {
        LanguageMessage languageMessage = new LanguageMessage();
        languageMessage.setId(1);
        languageMessage.setLanguage("en");
        languageMessage.setTranslation("Translation");
        when(this.languageMessageService.getMessageById(anyInt())).thenReturn(languageMessage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/translations/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"translation\":\"Translation\",\"language\":\"en\"}"));
    }

    /**
     * Method under test: {@link LanguageMessageController#getMessage(int)}
     */
    @Test
    void testGetMessage2() throws Exception {
        LanguageMessage languageMessage = new LanguageMessage();
        languageMessage.setId(1);
        languageMessage.setLanguage("en");
        languageMessage.setTranslation("Translation");
        when(this.languageMessageService.getMessageById(anyInt())).thenReturn(languageMessage);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/translations/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"translation\":\"Translation\",\"language\":\"en\"}"));
    }

    /**
     * Method under test: {@link LanguageMessageController#getTranslationByLang(String)}
     */
    @Test
    void testGetTranslationByLang() throws Exception {
        when(this.languageMessageService.findByLanguage((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello/{language}", "en");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LanguageMessageController#getTranslationByLang(String)}
     */
    @Test
    void testGetTranslationByLang2() throws Exception {
        when(this.languageMessageService.findByLanguage((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/hello/{language}", "en");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link LanguageMessageController#hello()}
     */
    @Test
    void testHello() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello.html");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello World2"));
    }

    /**
     * Method under test: {@link LanguageMessageController#hello()}
     */
    @Test
    void testHello2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/hello.html");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello World2"));
    }




    /**
     * Method under test: {@link LanguageMessageController#saveMessage(LanguageMessage)}
     */
    @Test
    void testSaveMessage() throws Exception {
        doNothing().when(this.languageMessageService).saveOrUpdate((LanguageMessage) any());

        LanguageMessage languageMessage = new LanguageMessage();
        languageMessage.setId(1);
        languageMessage.setLanguage("en");
        languageMessage.setTranslation("Translation");
        String content = (new ObjectMapper()).writeValueAsString(languageMessage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/translations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link LanguageMessageController#secureHello()}
     */
    @Test
    void testSecureHello() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/secure/hello");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Secure Hello"));
    }

    /**
     * Method under test: {@link LanguageMessageController#secureHello()}
     */
    @Test
    void testSecureHello2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/secure/hello");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Secure Hello"));
    }

    /**
     * Method under test: {@link LanguageMessageController#welcome()}
     */
    @Test
    void testWelcome() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello-rest");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

    /**
     * Method under test: {@link LanguageMessageController#welcome()}
     */
    @Test
    void testWelcome2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/hello-rest");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Hello World"));
    }

    /**
     * Method under test: {@link LanguageMessageController#welcomeForTest(String)}
     */
    @Test
    void testWelcomeForTest() throws Exception {
        when(this.languageMessageService.getWelcomeMessage((String) any())).thenReturn("Welcome Message");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcomeForTest").param("name", "foo");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome Message"));
    }

    /**
     * Method under test: {@link LanguageMessageController#welcomeForTest(String)}
     */
    @Test
    void testWelcomeForTest2() throws Exception {
        when(this.languageMessageService.getWelcomeMessage((String) any())).thenReturn("Welcome Message");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/welcomeForTest");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("name", "foo");
        MockMvcBuilders.standaloneSetup(this.languageMessageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome Message"));
    }
}

