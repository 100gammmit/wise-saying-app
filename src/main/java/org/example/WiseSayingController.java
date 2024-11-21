package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class WiseSayingController {
    private WiseSayingService wiseSayingService = new WiseSayingService();
    private BufferedReader br;

    public WiseSayingController(BufferedReader br) {
        this.br = br;
    }

    public void setWiseSayingService(WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
    }

    public void AtStartRun() throws IOException {
        wiseSayingService.AtStartRun();
    }

    public void Regist() throws IOException {
        System.out.print("명언 : ");
        String wiseSaying = br.readLine();
        System.out.print("작가 : ");
        String writter = br.readLine();

        int id = wiseSayingService.RegistWiseSaying(wiseSaying, writter);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void ViewAll() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------");

        ArrayList<WiseSaying> allWiseSaying = wiseSayingService.ViewAllWiseSaying();

        for(WiseSaying ws : allWiseSaying) {
            System.out.println(ws.getId() + " / " + ws.getWritter() + " / " + ws.getSaying());
        }
    }

    public void Remove(int removeId) {
        try {
            wiseSayingService.RemoveWiseSaying(removeId);
            System.out.println(removeId + "번 명언이 삭제되었습니다.");
        } catch (Exception e) {
            System.out.println(removeId + "번 명언은 존재하지 않습니다.");
        }
    }

    public void Update(int updateId) throws IOException {
        WiseSaying ws = wiseSayingService.FindWiseSayingById(updateId);

        System.out.println("명언(기존) : " + ws.getSaying());
        System.out.print("명언 : ");
        String updateSaying = br.readLine();

        System.out.println("작가(기존) : " + ws.getWritter());
        System.out.print("작가 : ");
        String updateWritter = br.readLine();

        wiseSayingService.UpdateWiseSaying(updateId, updateSaying, updateWritter);
    }

    public void BuildData() throws IOException {
        wiseSayingService.BuildData();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

}
