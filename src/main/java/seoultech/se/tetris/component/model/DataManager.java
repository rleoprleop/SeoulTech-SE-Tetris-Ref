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
//        writer = new BufferedWriter(new FileWriter("src/main/java/seoultech/se/tetris/component/model/setting.json"));
//        writer.write(js.toJSONString());
//        writer.close();
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

    public void setKey(int left, int right, int down, int pause, int rotate, int hardDrop){
        JSONObject data = readData();
        data.put(KEY_LEFT, left);
        data.put(KEY_RIGHT, right);
        data.put(KEY_DOWN, down);
        data.put(KEY_PAUSE, pause);
        data.put(KEY_ROTATE, rotate);
        data.put(KEY_HARDDROP, hardDrop);
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

    public int getHeight(){
        String display = getDisplay();
        int height=0;
        switch (display) {
            case "small":
                height = 600;
                break;
            case "normal":
                height = 1200;
                break;
            case "big":
                height = 1800;
                break;
        }
        return height;
    }

    public int getWeight() {
            String display = getDisplay();
            int weight=0;
            switch (display) {
                case "small":
                    weight = 500;
                    break;
                case "normal":
                    weight = 1000;
                    break;
                case "big":
                    weight = 1500;
                    break;
            }
            return weight;
        }

    public void resetting(){
        JSONObject data = readData();

        data.put(KEY_LEVEL,data.get("ori_Level"));
        data.put(KEY_COLOR,data.get("ori_Color_weak"));
        data.put(KEY_DISPLAY,data.get("ori_Display"));
        data.put(KEY_LEFT,data.get("ori_left"));
        data.put(KEY_RIGHT,data.get("ori_right"));
        data.put(KEY_ROTATE,data.get("ori_rotate"));
        data.put(KEY_HARDDROP,data.get("ori_hardDrop"));
        data.put(KEY_PAUSE,data.get("ori_pause"));
        data.put(KEY_DOWN,data.get("ori_down"));

        writeData(data.toString());
    }
}