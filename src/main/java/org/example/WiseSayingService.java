package org.example;

import java.io.*;
import java.util.HashMap;

public class WiseSayingService {
    int id;

    private final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public void AtStartRun() throws IOException {
        wiseSayingRepository.getAllDB();
        this.id = wiseSayingRepository.getLastId();
    }

    public int RegistWiseSaying(String wiseSaying, String writter) throws IOException {
        wiseSayingRepository.saveWiseSaying(new WiseSaying(id, wiseSaying, writter));

        id++;
        wiseSayingRepository.setLastId(id);

        return id - 1;
    }

    public HashMap<Integer, WiseSaying> ViewAllWiseSaying() {
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
}
