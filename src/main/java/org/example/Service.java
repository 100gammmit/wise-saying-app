package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Service {
    int id;

    private final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public void AtStartRun() throws IOException {
        wiseSayingRepository.getAllDB();
        this.id = wiseSayingRepository.getLastId();
        wiseSayingRepository.setLastId(id);
    }

    public void RegistWiseSaying(BufferedReader br) throws IOException {
        System.out.print("명언 : ");
        String wiseSaying = br.readLine();
        System.out.print("작가 : ");
        String writter = br.readLine();
        wiseSayingRepository.saveWiseSaying(new WiseSaying(id, wiseSaying, writter));

        System.out.println(id + "번 명언이 등록되었습니다.");
        id++;
        wiseSayingRepository.setLastId(id);
    }

    public void ViewAllWiseSaying() throws IOException {
        HashMap<Integer, WiseSaying> allWiseSaying = wiseSayingRepository.findAll();
        WiseSaying nowWS = null;

        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------");
        for(int i : allWiseSaying.keySet()) {
            nowWS = allWiseSaying.get(i);
            System.out.println(nowWS.getId() + " / " + nowWS.getWritter() + " / " + nowWS.getSaying());
        }
    }

    public void RemoveWiseSaying(int id) {
        try {
            wiseSayingRepository.removeById(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } catch (Exception e) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    public void UpdateWiseSaying(int id, BufferedReader br) throws IOException {
        WiseSaying wiseSaying = wiseSayingRepository.findById(id);

        System.out.println("명언(기존) : " + wiseSaying.getSaying());
        System.out.print("명언 : ");
        String updateSaying = br.readLine();

        System.out.println("작가(기존) : " + wiseSaying.getWritter());
        System.out.print("작가 : ");
        String updateWritter = br.readLine();

        wiseSayingRepository.UpdateWiseSaying(id, updateSaying, updateWritter);
    }

    public void BuildData() throws IOException {
        wiseSayingRepository.BuildData();
    }
}
