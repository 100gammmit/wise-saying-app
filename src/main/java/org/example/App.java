package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final WiseSayingController wiseSayingController = new WiseSayingController(br);


    public void Run() throws IOException {
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
    }
}
