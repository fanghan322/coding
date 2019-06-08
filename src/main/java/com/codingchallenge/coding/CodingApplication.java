package com.codingchallenge.coding;

import com.codingchallenge.coding.service.DataRetrievingService;
import com.codingchallenge.coding.service.WriteOutDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import static java.lang.System.exit;

@SpringBootApplication
public class CodingApplication implements CommandLineRunner {

    @Autowired
    private WriteOutDataService writeOutDataService;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        SpringApplication.run(CodingApplication.class, args);
        long endTime = System.nanoTime();
        //time elapsed
        long timeElapsed = endTime - startTime;
        System.out.println("Elapsed time of the application in milliseconds: " + timeElapsed / 1000000);
    }
    @Override
    public void run(String... args) throws Exception {

        //Set up the input data in the requirement
        String file1 = "ccds1.csv";
        String file2 = "ccds2.tsv";
        String output = "output.csv";
        writeOutDataService.writeOutData(file1, file2, output);
    }


}
