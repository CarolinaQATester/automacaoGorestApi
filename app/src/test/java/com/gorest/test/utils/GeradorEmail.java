package com.gorest.test.utils;

import java.util.Random;

public class GeradorEmail {
    public static String generateRandomEmail() {
        String[] domains = {"example.com", "test.com", "dummy.net"}; // Lista de provedores de email fictícios
        int length = 10; // Comprimento desejado do nome do email

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Gera uma sequência aleatória de caracteres alfanuméricos para o nome do email
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(36);
            char c = (char) (index < 26 ? 'a' + index : '0' + index - 26);
            sb.append(c);
        }

        // Adiciona um provedor de email aleatório ao nome do email gerado
        String domain = domains[random.nextInt(domains.length)];
        sb.append("@").append(domain);

        return sb.toString();
    }
}