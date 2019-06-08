package com.codingchallenge.coding.service;

import java.util.Map;

public interface DataRetrievingService {

    /**
     * retrieve data from csv file
     *
     * @return
     */
    void retrieveDataFromFile(String filename, Map<String, String> idUa);

    /**
     * retrieve data from csv file
     *
     * @return
     */
    void retrieveDataFromFile(String filename, Map<String, String>  idCountry, Map<String, String>  idF1, Map<String, String>  idF2, Map<String, String>  idF3);
}
