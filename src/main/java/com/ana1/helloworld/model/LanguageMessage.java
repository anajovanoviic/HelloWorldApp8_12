package com.ana1.helloworld.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Data
@ToString
//dodato za postgre
@Table(name = "languages")
@EntityListeners(AuditingEntityListener.class)
//dodato za postgre
public class LanguageMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Column(name = "language", nullable = false)
    private String language;

    public LanguageMessage() {
    }

    public LanguageMessage(int id, String translation, String language) {
        this.id = id;
        this.translation = translation;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
