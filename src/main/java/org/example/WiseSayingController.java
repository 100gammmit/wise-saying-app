package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class WiseSayingController {
    private WiseSayingService wiseSayingService = new WiseSayingService();
    private BufferedReader br;

    public WiseSayingController(BufferedReader br) {
        this.br = br;
    }

    public void setWiseSayingService(WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
    }

    public void atStartRun() throws IOException {
        wiseSayingService.atStartRun();
    }

    public void regist() throws IOException {
        System.out.print("명언 : ");
        String wiseSaying = br.readLine();
        System.out.print("작가 : ");
        String writter = br.readLine();

        int id = wiseSayingService.registWiseSaying(wiseSaying, writter);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void viewAll() {
        viewWiseSayingList(wiseSayingService.viewAllWiseSaying());
    }

    public void remove(int removeId) {
        try {
            wiseSayingService.removeWiseSaying(removeId);
            System.out.println(removeId + "번 명언이 삭제되었습니다.");
        } catch (Exception e) {
            System.out.println(removeId + "번 명언은 존재하지 않습니다.");
        }
    }

    public void update(int updateId) throws IOException {
        WiseSaying ws = wiseSayingService.findWiseSayingById(updateId);

        System.out.println("명언(기존) : " + ws.getSaying());
        System.out.print("명언 : ");
        String updateSaying = br.readLine();

        System.out.println("작가(기존) : " + ws.getWritter());
        System.out.print("작가 : ");
        String updateWritter = br.readLine();

        wiseSayingService.updateWiseSaying(updateId, updateSaying, updateWritter);
    }

    public void buildData() throws IOException {
        wiseSayingService.buildData();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    public void searchResult(String cmd) throws IOException {
        String type = cmd.substring(cmd.indexOf("=")+1, cmd.indexOf("&"));
        String keyword = cmd.substring(cmd.lastIndexOf("=")+1);

        System.out.println("------------------------");
        System.out.println("검색타입 : " + type);
        System.out.println("검색어 : " + keyword);
        System.out.println("------------------------");

        viewWiseSayingList(wiseSayingService.searchWiseSaying(keyword, type));
    }

    private void viewWiseSayingList(ArrayList<WiseSaying> wiseSayingArrayList) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------");

        for(WiseSaying ws : wiseSayingArrayList) {
            System.out.println(ws.getId() + " / " + ws.getWritter() + " / " + ws.getSaying());
        }
    }
}
