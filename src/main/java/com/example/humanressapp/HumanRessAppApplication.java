package com.example.humanressapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HumanRessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanRessAppApplication.class, args);
    }

    // iş ilanı açılırken açan kişinin
    // tipini kont etirilem serviste
    // eğer farklı kişi açmaya çalışırsa hata fırlatsın öyle bir çözüm bulsak ? emre

}
