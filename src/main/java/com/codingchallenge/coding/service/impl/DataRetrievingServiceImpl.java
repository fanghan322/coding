package com.codingchallenge.coding.service.impl;

import com.codingchallenge.coding.service.DataRetrievingService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Map;

@Service
public class DataRetrievingServiceImpl implements DataRetrievingService {
    @Override
    public void retrieveDataFromFile(String filename, Map<String, String> idUa) {
        ClassPathResource res = new ClassPathResource(filename);
        String splitBy = "\".\"";
        try {
            File inputF = res.getFile();
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFS));
            reader.lines().forEach(line -> {
                String[] splits = line.split(splitBy);
                String key = removeQuotes(splits[0]);
                String value = removeQuotes(splits[1]);
                idUa.put(key, value);
            });
            int size  = idUa.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String removeQuotes(String input){
        String returnValue = input;
        if (returnValue.startsWith("\"")){
            returnValue = returnValue.substring(1, returnValue.length());
        }
        if (returnValue.endsWith("\"")){
            returnValue = returnValue.substring(0, returnValue.length() - 1);
        }
        return returnValue;
    }

    @Override
    public void retrieveDataFromFile(String filename, Map<String, String>  idCountry, Map<String, String>  idF1, Map<String, String>  idF2, Map<String, String>  idF3){
        ClassPathResource res = new ClassPathResource(filename);
        String splitBy = "\t";
        try {
            File inputF = res.getFile();
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFS));
            reader.lines().forEach(line -> {
                String[] splits = line.split(splitBy);
                if (splits.length > 1) {
                    idCountry.put(splits[0], splits[1]);
                }
                if (splits.length > 2) {
                    idF1.put(splits[0], splits[2]);
                }
                if (splits.length > 3) {
                    idF2.put(splits[0], splits[3]);
                }
                if (splits.length > 4) {
                    idF3.put(splits[0], splits[4]);
                }
            });
            int size0  = idCountry.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
