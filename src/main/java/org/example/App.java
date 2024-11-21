package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private WiseSayingController wiseSayingController = new WiseSayingController(br);


    public void Run() {
        try {
            wiseSayingController.AtStartRun();
            System.out.println("== 명언 앱 ==");

            String command = "";
            while (true) {
                System.out.print("명령) ");
                command = br.readLine();

                if (command.equals("종료")) {
                    break;
                }
                if (command.equals("등록")) {
                    wiseSayingController.Regist();
                }

                if (command.equals("목록")) {
                    wiseSayingController.ViewAll();
                }

                if (command.contains("삭제")) {
                    // 파라미터로 String에서 int만 추출하며 파싱
                    wiseSayingController.Remove(Integer.parseInt(command.replaceAll("[^0-9]", "")));
                }

                if (command.contains("수정")) {
                    // 파라미터로 String에서 int만 추출하며 파싱
                    wiseSayingController.Update(Integer.parseInt(command.replaceAll("[^0-9]", "")));
                }

                if (command.contains("빌드")) {
                    wiseSayingController.BuildData();
                }
            }
        } catch (Exception e) {
            System.out.println("오류 발생 : \n" + e);
        }
    }


    public App() {
    }

    // 테스트용 main단에서 사용 금지
    public App(BufferedReader br, WiseSayingController wiseSayingController) {
        this.br = br;
        this.wiseSayingController = wiseSayingController;
    }

}
