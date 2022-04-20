package seoultech.se.tetris.component.model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreDataManager {
    private final String KEY_SCORE = "점수";
    private final String KEY_NAME = "이름";
    private final String KEY_LEVEL = "난이도";

    private final String KEY_NORM = "normalScore";
    private final String KEY_ITEM = "itemScore";
    private final String FILEPATH = "src/main/java/seoultech/se/tetris/component/model/ScoreData.json";
    private final String column[]={"순위","Player Name","Level","Score"};

    private ScoreDataManager() {

    }

    private static class LazyHolder {
        public static final ScoreDataManager dataManager = new ScoreDataManager();
    }

    public static ScoreDataManager getInstance() {
        return LazyHolder.dataManager;
    }

   public String getItemKey(){
        return this.KEY_ITEM;
   }

   public String getNormKey(){
        return this.KEY_NORM;
   }

    private JSONObject readData() {
        JSONObject scoreData = new JSONObject();
        try{
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(FILEPATH);
            scoreData = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }

        return scoreData;
    }

    private void writeData(String data){
        try {
            File jsonFile = new File(FILEPATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(data);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void addScoreData(String name, int score, String mode){
        JSONObject scoreData = readData();
        JSONArray scoreArr = (JSONArray) scoreData.get(mode);

//        JSONArray scoreData = readData();
        JSONObject data = new JSONObject();
        if(scoreArr.size() == 10){
            scoreArr.remove(9);
        }
        data.put(KEY_SCORE, score);
        data.put(KEY_NAME, name);
        data.put(KEY_LEVEL, DataManager.getInstance().getLevel());
        scoreArr.add(data);
        scoreData.put(mode, scoreArr);
        fetchData(scoreData, mode);
    }

    public void fetchData (JSONObject scoreData, String mode){
        System.out.println(scoreData.get(KEY_NORM).toString());
        System.out.println(scoreData.get(KEY_ITEM).toString());
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        JSONArray scoreArr = (JSONArray) scoreData.get(mode);
        for(int i = 0; i<scoreArr.size(); i++) {
            jsonValues.add((JSONObject) scoreArr.get(i));
        }

        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                int scoreA;
                int scoreB;

                scoreA = Integer.parseInt(a.get(KEY_SCORE).toString());
                scoreB = Integer.parseInt(b.get(KEY_SCORE).toString());

                return -Integer.compare(scoreA, scoreB);
            }

        });
        JSONObject keyArr = new JSONObject();
        if(mode == KEY_ITEM) {
            keyArr.put(KEY_NORM, (JSONArray) scoreData.get(KEY_NORM));
        }
        else {
            keyArr.put(KEY_ITEM, (JSONArray) scoreData.get(KEY_ITEM));
        }
        keyArr.put(mode, jsonValues);
        writeData(keyArr.toString());
    }


    public int getLastScore(String mode) {
        int score = 0;
        JSONObject scoreData = readData();
        JSONArray scoreArr = (JSONArray)scoreData.get(mode);

        if(scoreArr.size() < 10) return 0;

        else {
            int lastIdx = scoreArr.size() - 1;
            JSONObject element = (JSONObject) scoreArr.get(lastIdx);
            score = Integer.parseInt(element.get(KEY_SCORE).toString());
            return score;
        }
    }

    public int getRowsFromTable(String name, int score, String mode){
        int idx = 0;
        JSONObject scoreData = readData();
        JSONArray scoreArr = (JSONArray)scoreData.get(mode);
        int scoreArrSize = scoreArr.size();

        for(int i = 0; i<scoreArrSize; i++){
            JSONObject element = (JSONObject)scoreArr.get(i);
            int sc =Integer.parseInt(element.get(KEY_SCORE).toString());
            String str = element.get(KEY_NAME).toString();
            String level = element.get(KEY_LEVEL).toString();

            if(str.equals(name) && score == sc && level.equals(DataManager.getInstance().getLevel())){
                idx = i;
                break;
            }
        }
        return idx;
    }

    public Object[][] getObjectData(String mode){
        JSONObject scoreData = readData();

        JSONArray scoreArr = (JSONArray)scoreData.get(mode);
        Object objectData[][] = new Object[scoreArr.size()][4];

        for(int i = 0; i< scoreArr.size(); i++){
            JSONObject element = (JSONObject) scoreArr.get(i);
            objectData[i][0] = Integer.toString(i+1);
            objectData[i][1] = element.get(KEY_NAME).toString();
            objectData[i][2] = element.get(KEY_LEVEL).toString();
            objectData[i][3] = element.get(KEY_SCORE).toString();
        }
        return objectData;
    }

    public JTable getTable(String mode){
        JTable scoreTable = new JTable(getObjectData(mode), column);
        scoreTable.setEnabled(false);
        return scoreTable;
    }

    public void resetting(String mode) {
        JSONArray clearData = new JSONArray();
        JSONObject keyArr = new JSONObject();
        JSONObject scoreData = readData();
        JSONArray scoreArr = (JSONArray) scoreData.get(mode);
        if(mode == KEY_NORM){
            keyArr.put(KEY_ITEM, scoreData.get(KEY_ITEM));
        }
        else {
            keyArr.put(KEY_NORM, scoreData.get(KEY_NORM));
        }

        keyArr.put(mode, clearData);
        writeData(keyArr.toString());
    }
}