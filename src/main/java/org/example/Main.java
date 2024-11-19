package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Service service = new Service();
        service.AtStartRun();
        System.out.println("== 명언 앱 ==");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while (true) {
            System.out.print("명령) ");
            command = br.readLine();

            if (command.equals("종료")) {
                break;
            }
            if (command.equals("등록")) {
                service.RegistWiseSaying(br);
            }

            if (command.equals("목록")) {
                service.ViewAllWiseSaying();
            }

            if (command.contains("삭제")) {
                service.RemoveWiseSaying(Integer.parseInt(command.replaceAll("[^0-9]", "")));
            }

            if (command.contains("수정")) {
                service.UpdateWiseSaying(Integer.parseInt(command.replaceAll("[^0-9]", "")), br);
            }

            if (command.contains("빌드")) {
                service.BuildData();
            }


        }
    }
}