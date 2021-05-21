package org.springframework.boot.crackthecode.service;

import org.springframework.boot.crackthecode.models.Game;
import org.springframework.boot.crackthecode.models.Guess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CrackTheCodeServiceLayerImpl implements CrackTheCodeServiceLayer {
    @Override
    public String generateAnswer() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        String result = "";
        for(int i = 0; i < 4; i++){
            result += numbers.get(i).toString();
        }
        return result;
    }

    @Override
    public String calculate(String userGuess, String answer) {
        int exact = 0;
        int partial = 0;

        System.out.println(answer);
        System.out.println(userGuess);

        if (userGuess.equals("") || userGuess.length()!=answer.length()) {
            exact = 0;
            partial = 0;
        }
        else if (userGuess.equals(answer)) {
            exact = 4;
            partial = 0;
        }
        else {
            String[] answerArray = answer.split("");
            String[] guessArray = userGuess.split("");

            List<String> answerList = new ArrayList<>(Arrays.asList(answerArray));
            List<String> guessList = new ArrayList<>(Arrays.asList(guessArray));


            for (int counter = 0; counter < answerList.size(); counter++) {
                if (answerList.get(counter).equals(guessList.get(counter))) {
                    exact += 1;
                }
                else if (guessList.contains(answerList.get(counter))) {
                    partial += 1;
                }
            }
        }
        String res = "e:" + exact + ":p:" + partial;
        return res;
    }
}
