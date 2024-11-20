package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WiseSayingControllerTest {
    public static class TestUtil {
        // gen == generate 생성하다.
        // 테스트용 스캐너 생성
        public static BufferedReader genBufferReader(final String input) {
            final InputStream in = new ByteArrayInputStream(input.getBytes());

            return new BufferedReader(new InputStreamReader(in));
        }

        // System.out의 출력을 스트림으로 받기
        public static ByteArrayOutputStream setOutToByteArray() {
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            return output;
        }

        // setOutToByteArray 함수의 사용을 완료한 후 정리하는 함수, 출력을 다시 정상화 하는 함수
        public static void clearSetOutToByteArray(final ByteArrayOutputStream output) {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            try {
                output.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /*@BeforeEach
        void BeforeEach() throws IOException {
            wiseSayingController.AtStartRun();
        }*/

        @DisplayName("명언 등록")
        @Test
        void Regist() throws IOException {
            String cmd = """
                    등록
                    테스트명언
                    테스트작가
                    """;
            BufferedReader br = genBufferReader(cmd);
            String out = br.readLine();

            System.out.println("out = " + out);
        }

    }



}