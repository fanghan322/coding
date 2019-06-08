package com.codingchallenge.coding.service.impl;

import com.codingchallenge.coding.service.DataRetrievingService;
import com.codingchallenge.coding.service.WriteOutDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class WriteOutDataServiceImpl implements WriteOutDataService {
    @Autowired
    private DataRetrievingService dataRetrievingService;

    @Override
    public void writeOutData(String file1, String file2, String output) {
        Map<String, String> idUa = new HashMap<>();
        Map<String, String>  idCountry = new HashMap<>();
        Map<String, String>  idF1 = new HashMap<>();
        Map<String, String>  idF2 = new HashMap<>();
        Map<String, String>  idF3 = new HashMap<>();
        dataRetrievingService.retrieveDataFromFile(file1, idUa);
        dataRetrievingService.retrieveDataFromFile(file2, idCountry, idF1, idF2, idF3);
        String[] eus = {"BE", "BG", "CZ", "DK", "DE", "EE", "IE", "EL", "ES", "FR", "HR", "IT", "CY",
                "LV", "LT", "LU", "HU", "MT", "NL", "AT", "PL", "PT", "RO", "SI", "SK", "FI",
                "SE", "UK"};
        BufferedWriter bw = null;
        try {
            String outputDir = System.getProperty("user.home") + "/output.txt";
            FileWriter fw = new FileWriter(outputDir);
            for (Map.Entry<String, String> entry : idUa.entrySet()) {
                String id = entry.getKey();
                String country = idCountry.get(id);
                String ua = entry.getValue();
                String f1 = idF1.get(id);
                String f2 = idF2.get(id);
                String f3 = idF3.get(id);
                List<String> euList = Arrays.asList(eus);
                if (!StringUtils.isEmpty(country) && !euList.contains(country)) {
                    String outputString = new StringBuilder().append(id)
                            .append(",")
                            .append(country)
                            .append(",")
                            .append(ua)
                            .append(",")
                            .append(f1)
                            .append(",")
                            .append(f2)
                            .append(",")
                            .append(f3)
                            .append("\n")
                            .toString();
                    fw.write(outputString);
                    fw.flush();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }
}
