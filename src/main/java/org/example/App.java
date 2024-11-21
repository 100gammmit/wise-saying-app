package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private WiseSayingController wiseSayingController = new WiseSayingController(br);


    public void run() {
        try {
            wiseSayingController.atStartRun();
            System.out.println("== 명언 앱 ==");

            String command = "";
            while (true) {
                System.out.print("명령) ");
                command = br.readLine();

                if (command.equals("종료")) break;

                else if (command.equals("등록")) wiseSayingController.regist();

                else if (command.contains("목록")) {
                    if (command.contains("?")) wiseSayingController.searchResult(command);
                    else wiseSayingController.viewAll();
                }

                // 파라미터로 String에서 int만 추출하며 파싱
                else if (command.contains("삭제"))
                    wiseSayingController.remove(Integer.parseInt(command.replaceAll("[^0-9]", "")));

                else if (command.contains("수정"))
                    wiseSayingController.update(Integer.parseInt(command.replaceAll("[^0-9]", "")));

                else if (command.contains("빌드")) wiseSayingController.buildData();

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
