package org.springframework.boot.crackthecode.service;

import org.springframework.boot.crackthecode.models.Game;
import org.springframework.boot.crackthecode.models.Guess;

public interface CrackTheCodeServiceLayer {

    String generateAnswer();
    String calculate(String userGuess, String answer);

}
