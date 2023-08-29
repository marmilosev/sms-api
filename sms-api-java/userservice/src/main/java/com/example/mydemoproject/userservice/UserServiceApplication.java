package com.example.mydemoproject.userservice;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);

        // functional programming
        List<Integer> numbers = List.of(1, 2, 3);
        List<Integer> numbersDoubled = map(numbers, x -> x * 2);

        System.out.println(numbersDoubled);

        //smart cast
        String stringText = "Hello world!";
        String nullableText = null;

        printStringLength(stringText);
        printStringLength(nullableText);

        // ternary operator
        int x = 2;

        String result = (x + 2 == 4) ? "yes" : "no";
        System.out.println("X equals 2 - " + result);

        // if - switch
        int dayOfWeek = 3;
        String dayName;

        switch (dayOfWeek) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            default:
                dayName = "Weekend";
                break;
        }

        System.out.println("It's " + dayName);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function){
        List<R> result = new ArrayList<>();
        for(T item : list){
            result.add(function.apply(item));
        }
        return result;
    }

    public static void printStringLength(String str) {
        if (str != null) {
            // No smart cast in Java, using instanceof and type casting
            System.out.println("String length: " + str.length());
        } else {
            System.out.println("String is null");
        }
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
