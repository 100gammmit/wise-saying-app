package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WiseSayingRepository {
    HashMap<Integer, WiseSaying> DB = new HashMap<>();
    public void getAllDB() throws IOException {
        File lIdFile = new File(".\\src\\main\\db\\wiseSaying\\lastId.txt");
        File wsFile = null;

        BufferedReader br = new BufferedReader(new FileReader(lIdFile));
        int lId = Integer.parseInt(br.readLine());
        String line = "", saying = "", writter = "";

        for(int i = lId; i > 0; i--) {
            wsFile = new File (".\\src\\main\\db\\wiseSaying\\" + i + ".json");
            if(wsFile.exists()){
                br = new BufferedReader(new FileReader(wsFile));
                while ((line = br.readLine()) != null) {
                    if (line.contains("\"content\": ")) {
                        saying = line.split("\"")[3];
                    }
                    if (line.contains("\"author\": ")) {
                        writter = line.split("\"")[3];
                    }
                }
                DB.put(i, new WiseSaying(i, saying, writter));
            }
        }
        br.close();
    }

    public void setLastId(int id) {
        File lastId = new File(".\\src\\main\\db\\wiseSaying\\lastId.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(lastId));
            bw.write(String.valueOf(id));

            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getLastId() throws IOException {
        File lastId = new File(".\\src\\main\\db\\wiseSaying\\lastId.txt");
        int id = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(lastId));
            id = Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public void saveWiseSaying(WiseSaying wiseSaying) throws IOException {
        File nsw = new File(".\\src\\main\\db\\wiseSaying\\" + wiseSaying.getId() + ".json");
        nsw.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(nsw));
        try {
            WriteWiseSayingFile(wiseSaying, bw);

            bw.flush();
            bw.close();

            DB.put(wiseSaying.getId(), wiseSaying);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    public HashMap<Integer, WiseSaying> findAll() {
        return DB;
    }

    public WiseSaying findById(int id) {
        return DB.get(id);
    }

    public void removeById(int id) {
        File rmsw = new File(".\\src\\main\\db\\wiseSaying\\" + id + ".json");
        if (!rmsw.exists()) throw new RuntimeException();

        System.gc();
        rmsw.delete();
        DB.remove(id);
    }

    public void UpdateWiseSaying(int id, String wiseSaying, String writter) throws IOException {
        WiseSaying updateWS = findById(id);
        updateWS.setSaying(wiseSaying);
        updateWS.setWritter(writter);

        saveWiseSaying(updateWS);
    }

    public void BuildData() throws IOException {
        File data = new File(".\\src\\main\\db\\wiseSaying\\data.json");
        BufferedWriter bw = new BufferedWriter(new FileWriter(data));
        WiseSaying wiseSaying = null;

        // 마지막 명언에 콤마','가 안들어가게 하기 위해서 ArrayList로 따로 정리하여 마지막 index 추출
        ArrayList<WiseSaying> allWS = new ArrayList<>();
        for(int k : DB.keySet()) allWS.add(DB.get(k));

        bw.write("[");
        bw.newLine();
        for(int i=0; i < allWS.size(); i++){
            wiseSaying = allWS.get(i);
            try {
                WriteWiseSayingFile(wiseSaying, bw);
                if(i != allWS.size()-1) {
                    bw.write(",");
                    bw.newLine();
                }

            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }
        bw.newLine();
        bw.write("]");

        bw.flush();
        bw.close();
    }

    // 명언 파일 작성 양식 매서드
    // 중복되는 코드이기에 매서드로 만듦
    private void WriteWiseSayingFile(WiseSaying wiseSaying, BufferedWriter bw) throws IOException {
        bw.write("{");
        bw.newLine();
        bw.write("\"id\": " + wiseSaying.getId() + ",");
        bw.newLine();
        bw.write("\"content\": \"" + wiseSaying.getSaying() + "\",");
        bw.newLine();
        bw.write("\"author\": \"" + wiseSaying.getWritter() + "\"");
        bw.newLine();
        bw.write("}");
    }
}
