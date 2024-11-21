package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WiseSayingRepository {
    ArrayList<WiseSaying> DB = new ArrayList<>();
    private String dbDir = ".\\src\\main\\db\\wiseSaying\\";

    //테스트용 DB디렉토리 대체용
    public void setDbDir(String dbDir) {
        this.dbDir = dbDir;
    }

    public void getAllDB() throws IOException {
        File lIdFile = new File(dbDir + "lastId.txt");
        File wsFile = null;

        BufferedReader br = new BufferedReader(new FileReader(lIdFile));
        int lId = Integer.parseInt(br.readLine());
        String line = "", saying = "", writter = "";

        for (int i = lId; i > 0; i--) {
            wsFile = new File(dbDir + i + ".json");
            if (wsFile.exists()) {
                br = new BufferedReader(new FileReader(wsFile));
                while ((line = br.readLine()) != null) {
                    if (line.contains("\"content\": ")) {
                        saying = line.split("\"")[3];
                    }
                    if (line.contains("\"author\": ")) {
                        writter = line.split("\"")[3];
                    }
                }
                DB.add(new WiseSaying(i, saying, writter));
            }
        }
        br.close();
    }

    public void setLastId(int id) {
        File lastId = new File(dbDir + "lastId.txt");
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
        File lastId = new File(dbDir + "lastId.txt");
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
        File nsw = new File(dbDir + wiseSaying.getId() + ".json");
        nsw.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(nsw));
        WriteWiseSayingJSONFile(wiseSaying, bw);

        bw.flush();
        bw.close();

        DB.addFirst(wiseSaying);
    }

    public ArrayList<WiseSaying> findAll() {
        return DB;
    }

    public WiseSaying findById(int id) {
        WiseSaying result = null;
        for (WiseSaying ws : DB) {
            if (ws.getId() == id) {
                result = ws;
                break;
            }
        }
        return result;
    }

    public void removeById(int id) {
        File rmsw = new File(dbDir + id + ".json");
        if (!rmsw.exists()) throw new RuntimeException();

        for (int i = 0; i < DB.size(); i++) {
            if(DB.get(i).getId() == id) {
                DB.remove(i);
                break;
            }
        }
        System.gc();
        rmsw.delete();
    }

    public void UpdateWiseSaying(int id, String wiseSaying, String writter) throws IOException {
        WiseSaying updateWS = findById(id);
        updateWS.setSaying(wiseSaying);
        updateWS.setWritter(writter);

        File uSW = new File(dbDir + updateWS.getId() + ".json");
        BufferedWriter bw = new BufferedWriter(new FileWriter(uSW));
        WriteWiseSayingJSONFile(updateWS, bw);

        bw.flush();
        bw.close();
    }

    public void BuildData() throws IOException {
        File data = new File(dbDir + "data.json");
        BufferedWriter bw = new BufferedWriter(new FileWriter(data));
        WiseSaying wiseSaying = null;

        bw.write("[");
        bw.newLine();
        for (int i = 0; i < DB.size(); i++) {
            wiseSaying = DB.get(i);

            WriteWiseSayingJSONFile(wiseSaying, bw);
            if (i != DB.size() - 1) {
                bw.write(",");
                bw.newLine();
            }


        }
        bw.newLine();
        bw.write("]");

        bw.flush();
        bw.close();
    }

    public ArrayList<WiseSaying> SerarchWiseSayingsBySaying(String keyword) {
        ArrayList<WiseSaying> result = new ArrayList<>();
        for (WiseSaying ws : DB) {
            if (ws.getSaying().contains(keyword)) result.add(ws);
        }
        return result;
    }

    public ArrayList<WiseSaying> SerarchWiseSayingsByWritter(String keyword) {
        ArrayList<WiseSaying> result = new ArrayList<>();
        for (WiseSaying ws : DB) {
            if (ws.getWritter().contains(keyword)) result.add(ws);
        }
        return result;
    }

    // 명언 파일 작성 양식 매서드
    // 중복되는 코드이기에 매서드로 만듦
    private void WriteWiseSayingJSONFile(WiseSaying wiseSaying, BufferedWriter bw) throws IOException {
        bw.write("{\n\t\"id\": " + wiseSaying.getId() + ",\n" +
                "\t\"content\": \"" + wiseSaying.getSaying() + "\",\n" +
                "\t\"author\": \"" + wiseSaying.getWritter() + "\"\n}");
    }
}
