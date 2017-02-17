package com.yegor.dollar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is looking for exchange rate of the currency in http://finance.i.ua/.
 * Created by YegorKost on 15.02.2017.
 */
public class DollarExchangeRate {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://finance.i.ua/");
        List<String> result = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))){

            getInstructionMessage();

            String currency;
            while ((currency = in.readLine()) != null) {
                if (!currency.contains("exit") ) {
                    currency = currency.toLowerCase();

                    if (currency.equals("usd") || currency.equals("eur") || currency.equals("rub")) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.contains(currency)) {
                                result.add(line);
                            }
                        }

                        getToScreen(currency, result, "buy");
                        getToScreen(currency, result, "sell");

                        System.out.print("Next currency (USD, EUR or RUB): ");
                    } else {
                        System.out.println("Enter correct currency (USD, EUR or RUB): ");
                    }
                } else {
                    return;
                }
            }
        }
    }

    private static void getInstructionMessage() {
        System.out.print("Enter currency (USD, EUR or RUB) to get the exchange rate from http://finance.i.ua/ " +
                "or type \"exit\" to finish: ");
    }

    private static List<String> getExchangeRate(String currency, List<String> result, String buyOrSell, String minMiddleMax) {
        List<String> intermediateResult = lookingForExchangeRate(currency, result, "tfoot");
        intermediateResult = lookingForExchangeRate(minMiddleMax, intermediateResult, "tr");
        intermediateResult = lookingForExchangeRate(buyOrSell, intermediateResult, "td");
        intermediateResult = lookingForExchangeRate(".", intermediateResult, "<");
        intermediateResult = lookingForExchangeRate(".", intermediateResult, ">");
        return intermediateResult;
    }

    private static List<String> lookingForExchangeRate(String searching, List<String> withSearching, String splitter){
        List<String> result = new ArrayList<>();
        for (String s: withSearching) {
            for (String ss: s.split(splitter)) {
                if (ss.contains(searching)) {
                    result.add(ss);
                }
            }
        }
        return result;
    }

    private static void getToScreen(String currency, List<String> result, String buyOrSell) {
        System.out.println(currency.toUpperCase() +
                ": middle " + buyOrSell + " - " + getExchangeRate(currency, result, buyOrSell, "Средний"));
        System.out.println(currency.toUpperCase() +
                ": max " + buyOrSell + " - " + getExchangeRate(currency, result, buyOrSell, "Максимальный"));
        System.out.println(currency.toUpperCase() +
                ": min " + buyOrSell + " - " + getExchangeRate(currency, result, buyOrSell, "Минимальный"));
    }
}
