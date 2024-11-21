package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WiseSayingService {
    private int id;

    private WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSayingService() {
    }

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public void AtStartRun() throws IOException {
        wiseSayingRepository.getAllDB();
        this.id = wiseSayingRepository.getLastId();
    }

    public int RegistWiseSaying(String wiseSaying, String writter) {
        try {
            wiseSayingRepository.saveWiseSaying(new WiseSaying(id, wiseSaying, writter));

            id++;
            wiseSayingRepository.setLastId(id);
        } catch(IOException e) {
            System.out.println(" = " + e);
        }
        return id - 1;
    }

    public ArrayList<WiseSaying> ViewAllWiseSaying() {
        return wiseSayingRepository.findAll();
    }

    public void RemoveWiseSaying(int id) {
        wiseSayingRepository.removeById(id);
    }

    public void UpdateWiseSaying(int id, String updateSaying, String updateWritter) throws IOException {
        wiseSayingRepository.UpdateWiseSaying(id, updateSaying, updateWritter);
    }

    public WiseSaying FindWiseSayingById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void BuildData() throws IOException {
        wiseSayingRepository.BuildData();
    }

    public ArrayList<WiseSaying> SearchWiseSaying(String keyword, String type) throws IOException {
        ArrayList<WiseSaying> result;
        if(type.equals("content")) {
            result = wiseSayingRepository.SerarchWiseSayingsBySaying(keyword);
        }
        else if(type.equals("author")) {
            result = wiseSayingRepository.SerarchWiseSayingsByWritter(keyword);
        }
        else throw new IOException();

        return result;
    }
}
