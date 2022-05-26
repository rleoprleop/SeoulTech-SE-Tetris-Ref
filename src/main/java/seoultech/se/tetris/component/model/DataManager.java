package seoultech.se.tetris.component.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class DataManager {
    private final String FILEPATH = "src/main/java/seoultech/se/tetris/component/model/setting.json";
    private final String KEY_LEVEL = "Level";
    private final String KEY_COLOR = "Color_weak";
    private final String KEY_DISPLAY = "Display";
    private final String KEY_LEFT = "left";
    private final String KEY_RIGHT = "right";
    private final String KEY_DOWN = "down";

    private final String KEY_ROTATE = "rotate";
    private final String KEY_HARDDROP = "hardDrop";
    private final String KEY_PAUSE = "pause";
    private final String KEY_LEFT2 = "left2";
    private final String KEY_RIGHT2 = "right2";
    private final String KEY_DOWN2 = "down2";

    private final String KEY_ROTATE2 = "rotate2";
    private final String KEY_HARDDROP2 = "hardDrop2";

    private final String KEY_MODE = "mode";

    private DataManager() {
    }

    private static class LazyHolder {
        public static final DataManager dataManager = new DataManager();
    }

    public static DataManager getInstance() {
        return DataManager.LazyHolder.dataManager;
    }

    private JSONObject readData() {
        JSONObject data = new JSONObject();
        try{
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(FILEPATH);
            data = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }

        return data;
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

    public void setMode(String mode){
        JSONObject data = readData();
        data.put(KEY_MODE, mode);
        writeData(data.toString());
    }

    public void setLevel(String lv){
        JSONObject data = readData();
        data.put(KEY_LEVEL, lv);
        writeData(data.toString());
    }
    public void setColor_weak(String colorweak){
        JSONObject data = readData();
        data.put(KEY_COLOR, colorweak);
        writeData(data.toString());
    }

    public void setDisplay(String dp){
        JSONObject data = readData();
        data.put(KEY_DISPLAY, dp);
        writeData(data.toString());
    }

    public void setLeft(int code) {
        JSONObject data = readData();
        data.put(KEY_LEFT, code);
        writeData(data.toString());
    }

    public void setRight(int code){
        JSONObject data = readData();
        data.put(KEY_RIGHT, code);
        writeData(data.toString());
    }

    public void setRotate(int code){
        JSONObject data = readData();
        data.put(KEY_ROTATE, code);
        writeData(data.toString());
    }

    public void setHarddrop(int code){
        JSONObject data = readData();
        data.put(KEY_HARDDROP, code);
        writeData(data.toString());
    }

    public void setPause(int code){
        JSONObject data = readData();
        data.put(KEY_PAUSE, code);
        writeData(data.toString());
    }

    public void setDown(int code){
        JSONObject data = readData();
        data.put(KEY_DOWN, code);
        writeData(data.toString());
    }

    public void setLeft2(int code) {
        JSONObject data = readData();
        data.put(KEY_LEFT2, code);
        writeData(data.toString());
    }

    public void setRight2(int code){
        JSONObject data = readData();
        data.put(KEY_RIGHT2, code);
        writeData(data.toString());
    }

    public void setRotate2(int code){
        JSONObject data = readData();
        data.put(KEY_ROTATE2, code);
        writeData(data.toString());
    }

    public void setHarddrop2(int code){
        JSONObject data = readData();
        data.put(KEY_HARDDROP2, code);
        writeData(data.toString());
    }

    public void setDown2(int code){
        JSONObject data = readData();
        data.put(KEY_DOWN2, code);
        writeData(data.toString());
    }
    public int getDisPlayWidth(){
        String display = getDisplay();
        int display_width = 0;
        switch (display){
            case "small":
                display_width = 500;
                break;
            case "normal":
                display_width = 700;
                break;
            case "big":
                display_width = 800;
                break;
        }
        return display_width;
    }
    public int getDisPlayHeight(){
        String display = getDisplay();
        int display_height=0;
        switch (display){
            case "small":
                display_height = 600;
                break;
            case "normal":
                display_height = 840;
                break;
            case "big":
                display_height = 960;
                break;
        }
        return display_height;
    }

    public void setKey(int keyArr[]){
        JSONObject data = readData();
        data.put(KEY_LEFT, keyArr[0]);
        data.put(KEY_RIGHT, keyArr[1]);
        data.put(KEY_DOWN, keyArr[2]);
        data.put(KEY_ROTATE, keyArr[3]);
        data.put(KEY_HARDDROP, keyArr[4]);
        data.put(KEY_PAUSE, keyArr[5]);

        if(keyArr.length > 6){
            data.put(KEY_LEFT2, keyArr[6]);
            data.put(KEY_RIGHT2, keyArr[7]);
            data.put(KEY_DOWN2, keyArr[8]);
            data.put(KEY_ROTATE2, keyArr[9]);
            data.put(KEY_HARDDROP2, keyArr[10]);

        }
        writeData(data.toString());
    }

    public String getMode(){
        JSONObject data = readData();
        return data.get(KEY_MODE).toString();
    }
    public String getLevel(){
        JSONObject data = readData();
        return data.get(KEY_LEVEL).toString();
    }
    public String getColor_weak(){
        JSONObject data = readData();
        return data.get(KEY_COLOR).toString();
    }
    public String getDisplay(){
        JSONObject data = readData();
        return data.get(KEY_DISPLAY).toString();
    }
    public int getLeft(){
        JSONObject data = readData();
        String stringData = data.get(KEY_LEFT).toString();
        return Integer.parseInt(stringData);
    }
    public int getRight() {
        JSONObject data = readData();
        String stringData = data.get(KEY_RIGHT).toString();
        return Integer.parseInt(stringData);
    }
    public int getRotate() {
        JSONObject data = readData();
        String stringData = data.get(KEY_ROTATE).toString();
        return Integer.parseInt(stringData);
    }

    public int getHarddrop() {
        JSONObject data = readData();
        String stringData = data.get(KEY_HARDDROP).toString();
        return Integer.parseInt(stringData);
    }

    public int getPause(){
        JSONObject data = readData();
        String stringData = data.get(KEY_PAUSE).toString();
        return Integer.parseInt(stringData);
    }

    public int getDown(){
        JSONObject data = readData();
        String stringData = data.get(KEY_DOWN).toString();
        return Integer.parseInt(stringData);
    }

    public int getLeft2(){
        JSONObject data = readData();
        String stringData = data.get(KEY_LEFT2).toString();
        return Integer.parseInt(stringData);
    }
    public int getRight2() {
        JSONObject data = readData();
        String stringData = data.get(KEY_RIGHT2).toString();
        return Integer.parseInt(stringData);
    }
    public int getRotate2() {
        JSONObject data = readData();
        String stringData = data.get(KEY_ROTATE2).toString();
        return Integer.parseInt(stringData);
    }

    public int getHarddrop2() {
        JSONObject data = readData();
        String stringData = data.get(KEY_HARDDROP2).toString();
        return Integer.parseInt(stringData);
    }

    public int getDown2(){
        JSONObject data = readData();
        String stringData = data.get(KEY_DOWN2).toString();
        return Integer.parseInt(stringData);
    }

    public void resetting(){
        JSONObject data = readData();

        data.put(KEY_LEVEL, "normal");
        data.put(KEY_COLOR, "off");
        data.put(KEY_DISPLAY, "normal");
        data.put(KEY_LEFT, 37);
        data.put(KEY_RIGHT, 39);
        data.put(KEY_ROTATE, 38);
        data.put(KEY_HARDDROP, 32);
        data.put(KEY_PAUSE, 27);
        data.put(KEY_DOWN, 40);


        data.put(KEY_LEFT2, 65);
        data.put(KEY_RIGHT2, 68);
        data.put(KEY_ROTATE2,87);
        data.put(KEY_HARDDROP2, 84);
        data.put(KEY_DOWN2, 83);

        writeData(data.toString());
    }
//    {"rotate":38,"down2":68,"hardDrop2":84,"Color_weak":"off","left2":65,"right":39,"down":40,"pause":27,"mode":"normalScore","left":37,"right2":68,"rotate2":87,"Level":"normal","hardDrop":32,"Display":"normal"}
}