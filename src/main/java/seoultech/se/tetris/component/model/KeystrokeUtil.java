package seoultech.se.tetris.component.model;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.KeyStroke;

/**
 * This source code was taken from a code example at http://javaalmanac.com/
 * and slightly altered to fit our purpose.
 * @author ole
 *
 */
public class KeystrokeUtil {
    private static HashMap<String, Integer> keyCode;
    private static HashMap<Integer, String> keyString;


    private KeystrokeUtil() {
    }

    private static class LazyHolder {
        public static final KeystrokeUtil dataManager = new KeystrokeUtil();
    }

    public static KeystrokeUtil getInstance() {
        return KeystrokeUtil.LazyHolder.dataManager;
    }

   private static void initialization(){
        keyCode = new HashMap<>();
        keyString = new HashMap<>();

        keyCode.put("COMMA", KeyEvent.VK_COMMA);                    keyString.put(KeyEvent.VK_COMMA, "COMMA");
        keyCode.put("PERIOD", KeyEvent.VK_PERIOD);                  keyString.put(KeyEvent.VK_PERIOD, "PERIOD");
       keyCode.put("SLASH", KeyEvent.VK_SLASH);                     keyString.put(KeyEvent.VK_SLASH, "SLASH");
       keyCode.put("SEMICOLON", KeyEvent.VK_SEMICOLON);             keyString.put(KeyEvent.VK_SEMICOLON, "SEMICOLON");
       keyCode.put("OPEN_BRACKET", KeyEvent.VK_OPEN_BRACKET);       keyString.put(KeyEvent.VK_OPEN_BRACKET, "OPEN_BRACKET");
       keyCode.put("BACK_SLASH", KeyEvent.VK_BACK_SLASH);           keyString.put(KeyEvent.VK_BACK_SLASH, "BACK_SLASH");
       keyCode.put("CLOSE_BRACKET", KeyEvent.VK_CLOSE_BRACKET);     keyString.put(KeyEvent.VK_CLOSE_BRACKET, "CLOSE_BRACKET");

       keyCode.put("ENTER", KeyEvent.VK_ENTER);                     keyString.put(KeyEvent.VK_ENTER, "ENTER");
       keyCode.put("BACK_SPACE", KeyEvent.VK_BACK_SPACE);           keyString.put(KeyEvent.VK_BACK_SPACE, "BACK_SPACE");
       keyCode.put("TAB", KeyEvent.VK_TAB);                         keyString.put(KeyEvent.VK_TAB, "TAB");
       keyCode.put("CANCEL", KeyEvent.VK_CANCEL);                   keyString.put(KeyEvent.VK_CANCEL, "CANCEL");
       keyCode.put("CLEAR", KeyEvent.VK_CLEAR);                     keyString.put(KeyEvent.VK_CLEAR, "CLEAR");
       keyCode.put("SHIFT", KeyEvent.VK_SHIFT);                     keyString.put(KeyEvent.VK_SHIFT, "SHIFT");
       keyCode.put("CONTROL", KeyEvent.VK_CONTROL);                 keyString.put(KeyEvent.VK_CONTROL, "CONTROL");

;      keyCode.put("ALT", KeyEvent.VK_ALT);                         keyString.put(KeyEvent.VK_ALT, "ALT");
       keyCode.put("PAUSE", KeyEvent.VK_PAUSE);                     keyString.put(KeyEvent.VK_PAUSE, "PAUSE");
       keyCode.put("CAPS_LOCK", KeyEvent.VK_CAPS_LOCK);             keyString.put(KeyEvent.VK_CAPS_LOCK, "CAPS_LOCK");
       keyCode.put("ESCAPE", KeyEvent.VK_ESCAPE);                   keyString.put(KeyEvent.VK_ESCAPE, "ESCAPE");
       keyCode.put("SPACE", KeyEvent.VK_SPACE);                     keyString.put(KeyEvent.VK_SPACE, "SPACE");
       keyCode.put("PAGE_UP", KeyEvent.VK_PAGE_UP);                 keyString.put(KeyEvent.VK_PAGE_UP, "PAGE_UP");
       keyCode.put("PAGE_DOWN", KeyEvent.VK_PAGE_DOWN);             keyString.put(KeyEvent.VK_PAGE_DOWN, "PAGE_DOWN");
       keyCode.put("END", KeyEvent.VK_END);                         keyString.put(KeyEvent.VK_END, "END");
       keyCode.put("HOME", KeyEvent.VK_HOME);                       keyString.put(KeyEvent.VK_HOME, "HOME");
       keyCode.put("LEFT", KeyEvent.VK_LEFT);                       keyString.put(KeyEvent.VK_LEFT, "LEFT");
       keyCode.put("UP", KeyEvent.VK_UP);                           keyString.put(KeyEvent.VK_UP, "UP");
       keyCode.put("RIGHT", KeyEvent.VK_RIGHT);                     keyString.put(KeyEvent.VK_RIGHT, "RIGHT");
       keyCode.put("DOWN", KeyEvent.VK_DOWN);                       keyString.put(KeyEvent.VK_DOWN, "DOWN");

       keyCode.put("MULTIPLY", KeyEvent.VK_MULTIPLY);               keyString.put(KeyEvent.VK_MULTIPLY, "MULTIPLY");
       keyCode.put("ADD", KeyEvent.VK_ADD);                         keyString.put(KeyEvent.VK_ADD, "ADD");
       keyCode.put("SEPARATOR", KeyEvent.VK_SEPARATOR);             keyString.put(KeyEvent.VK_SEPARATOR, "SEPARATOR");
       keyCode.put("SUBTRACT", KeyEvent.VK_SUBTRACT);               keyString.put(KeyEvent.VK_SUBTRACT, "SUBTRACT");
       keyCode.put("DECIMAL", KeyEvent.VK_DECIMAL);                 keyString.put(KeyEvent.VK_DECIMAL, "DECIMAL");
       keyCode.put("DIVIDE", KeyEvent.VK_DIVIDE);                   keyString.put(KeyEvent.VK_DIVIDE, "DIVIDE");
       keyCode.put("DELETE", KeyEvent.VK_DELETE);                   keyString.put(KeyEvent.VK_DELETE, "DELETE");
       keyCode.put("NUM_LOCK", KeyEvent.VK_NUM_LOCK);               keyString.put(KeyEvent.VK_NUM_LOCK, "NUM_LOCK");
       keyCode.put("SCROLL_LOCK", KeyEvent.VK_SCROLL_LOCK);         keyString.put(KeyEvent.VK_SCROLL_LOCK, "SCROLL_LOCK");

       keyCode.put("F1", KeyEvent.VK_F1);                           keyString.put(KeyEvent.VK_F1, "F1");
       keyCode.put("F2", KeyEvent.VK_F2);                           keyString.put(KeyEvent.VK_F2, "F2");
       keyCode.put("F3", KeyEvent.VK_F3);                           keyString.put(KeyEvent.VK_F3, "F3");
       keyCode.put("F4", KeyEvent.VK_F4);                           keyString.put(KeyEvent.VK_F4, "F4");
       keyCode.put("F5", KeyEvent.VK_F5);                           keyString.put(KeyEvent.VK_F5, "F5");
       keyCode.put("F6", KeyEvent.VK_F6);                           keyString.put(KeyEvent.VK_F6, "F6");
       keyCode.put("F7", KeyEvent.VK_F7);                           keyString.put(KeyEvent.VK_F7, "F7");
       keyCode.put("F8", KeyEvent.VK_F8);                           keyString.put(KeyEvent.VK_F8, "F8");
       keyCode.put("F9", KeyEvent.VK_F9);                           keyString.put(KeyEvent.VK_F9, "F9");
       keyCode.put("F10", KeyEvent.VK_F10);                         keyString.put(KeyEvent.VK_F10, "F10");
       keyCode.put("F11", KeyEvent.VK_F11);                         keyString.put(KeyEvent.VK_F11, "F11");
       keyCode.put("F12", KeyEvent.VK_F12);                         keyString.put(KeyEvent.VK_F12, "F12");
       keyCode.put("F13", KeyEvent.VK_F13);                         keyString.put(KeyEvent.VK_F13, "F13");
       keyCode.put("F14", KeyEvent.VK_F14);                         keyString.put(KeyEvent.VK_F14, "F14");
       keyCode.put("F15", KeyEvent.VK_F15);                         keyString.put(KeyEvent.VK_F15, "F15");
       keyCode.put("F16", KeyEvent.VK_F16);                         keyString.put(KeyEvent.VK_F16, "F16");
       keyCode.put("F17", KeyEvent.VK_F17);                         keyString.put(KeyEvent.VK_F17, "F17");
       keyCode.put("F18", KeyEvent.VK_F18);                         keyString.put(KeyEvent.VK_F18, "F18");
       keyCode.put("F19", KeyEvent.VK_F19);                         keyString.put(KeyEvent.VK_F19, "F19");
       keyCode.put("F20", KeyEvent.VK_F20);                         keyString.put(KeyEvent.VK_F20, "F20");
       keyCode.put("F21", KeyEvent.VK_F21);                         keyString.put(KeyEvent.VK_F21, "F21");
       keyCode.put("F22", KeyEvent.VK_F22);                         keyString.put(KeyEvent.VK_F22, "F22");
       keyCode.put("F23", KeyEvent.VK_F23);                         keyString.put(KeyEvent.VK_F23, "F23");
       keyCode.put("F24", KeyEvent.VK_F24);                         keyString.put(KeyEvent.VK_F24, "F24");

       keyCode.put("PRINTSCREEN", KeyEvent.VK_PRINTSCREEN);         keyString.put(KeyEvent.VK_PRINTSCREEN, "PRINTSCREEN");
       keyCode.put("INSERT", KeyEvent.VK_INSERT);                   keyString.put(KeyEvent.VK_INSERT, "INSERT");
       keyCode.put("HELP", KeyEvent.VK_HELP);                       keyString.put(KeyEvent.VK_HELP, "HELP");
       keyCode.put("META", KeyEvent.VK_META);                       keyString.put(KeyEvent.VK_META, "SUBTRAMETACT");
       keyCode.put("BACK_QUOTE", KeyEvent.VK_BACK_QUOTE);           keyString.put(KeyEvent.VK_BACK_QUOTE, "BACK_QUOTE");
       keyCode.put("QUOTE", KeyEvent.VK_QUOTE);                     keyString.put(KeyEvent.VK_QUOTE, "QUOTE");
       keyCode.put("KP_UP", KeyEvent.VK_KP_UP);                     keyString.put(KeyEvent.VK_KP_UP, "KP_UP");
       keyCode.put("KP_DOWN", KeyEvent.VK_KP_DOWN);                 keyString.put(KeyEvent.VK_KP_DOWN, "KP_DOWN");
       keyCode.put("KP_LEFT", KeyEvent.VK_KP_LEFT);                 keyString.put(KeyEvent.VK_KP_LEFT, "KP_LEFT");
       keyCode.put("KP_RIGHT", KeyEvent.VK_KP_RIGHT);               keyString.put(KeyEvent.VK_KP_RIGHT, "KP_RIGHT");


       keyCode.put("DEAD_GRAVE", KeyEvent.VK_DEAD_GRAVE);           keyString.put(KeyEvent.VK_DEAD_GRAVE, "DEAD_GRAVE");
       keyCode.put("DEAD_ACUTE", KeyEvent.VK_DEAD_ACUTE);           keyString.put(KeyEvent.VK_DEAD_ACUTE, "DEAD_ACUTE");
       keyCode.put("DEAD_CIRCUMFLEX", KeyEvent.VK_DEAD_CIRCUMFLEX); keyString.put(KeyEvent.VK_DEAD_CIRCUMFLEX, "DEAD_CIRCUMFLEX");
       keyCode.put("DEAD_TILDE", KeyEvent.VK_DEAD_TILDE);           keyString.put(KeyEvent.VK_DEAD_TILDE, "DEAD_TILDE");
       keyCode.put("DEAD_MACRON", KeyEvent.VK_DEAD_MACRON);         keyString.put(KeyEvent.VK_DEAD_MACRON, "DEAD_MACRON");
       keyCode.put("DEAD_BREVE", KeyEvent.VK_DEAD_BREVE);           keyString.put(KeyEvent.VK_DEAD_BREVE, "DEAD_BREVE");
       keyCode.put("DEAD_ABOVEDOT", KeyEvent.VK_DEAD_ABOVEDOT);     keyString.put(KeyEvent.VK_DEAD_ABOVEDOT, "DEAD_ABOVEDOT");
       keyCode.put("KP_LEFT", KeyEvent.VK_KP_LEFT);                 keyString.put(KeyEvent.VK_KP_LEFT, "KP_LEFT");
       keyCode.put("KP_RIGHT", KeyEvent.VK_KP_RIGHT);               keyString.put(KeyEvent.VK_KP_RIGHT, "KP_RIGHT");

//       case KeyEvent.: return "";
//       case KeyEvent.: return "";
//       case KeyEvent.VK_DEAD_DIAERESIS: return "DEAD_DIAERESIS";
//       case KeyEvent.VK_DEAD_ABOVERING: return "DEAD_ABOVERING";
//       case KeyEvent.VK_DEAD_DOUBLEACUTE: return "DEAD_DOUBLEACUTE";
//       case KeyEvent.VK_DEAD_CARON: return "DEAD_CARON";
//       case KeyEvent.VK_DEAD_CEDILLA: return "DEAD_CEDILLA";
//       case KeyEvent.VK_DEAD_OGONEK: return "DEAD_OGONEK";
//       case KeyEvent.VK_DEAD_IOTA: return "DEAD_IOTA";
//       case KeyEvent.VK_DEAD_VOICED_SOUND: return "DEAD_VOICED_SOUND";
//       case KeyEvent.VK_DEAD_SEMIVOICED_SOUND: return "DEAD_SEMIVOICED_SOUND";
    }

    public static String keyStroke2String(KeyStroke key) {
        if (key == null) return "";
        StringBuilder s = new StringBuilder(50);
        int m = key.getModifiers();

        if ((m & (InputEvent.CTRL_DOWN_MASK|InputEvent.CTRL_MASK)) != 0) {
            s.append("Ctrl+");
        }
        if ((m & (InputEvent.META_DOWN_MASK|InputEvent.META_MASK)) != 0) {
            s.append("Meta+");
        }
        if ((m & (InputEvent.ALT_DOWN_MASK|InputEvent.ALT_MASK)) != 0) {
            s.append("Alt+");
        }
        if ((m & (InputEvent.SHIFT_DOWN_MASK|InputEvent.SHIFT_MASK)) != 0) {
            s.append("Shift+");
        }
        if ((m & (InputEvent.BUTTON1_DOWN_MASK|InputEvent.BUTTON1_MASK)) != 0) {
            s.append("Button1+");
        }
        if ((m & (InputEvent.BUTTON2_DOWN_MASK|InputEvent.BUTTON2_MASK)) != 0) {
            s.append("Button2+");
        }
        if ((m & (InputEvent.BUTTON3_DOWN_MASK|InputEvent.BUTTON3_MASK)) != 0) {
            s.append("Button3+");
        }

        switch (key.getKeyEventType()) {
            case KeyEvent.KEY_TYPED:
                s.append(key.getKeyChar() + " ");
                break;
            case KeyEvent.KEY_PRESSED:
            case KeyEvent.KEY_RELEASED:
                s.append(getKeyText(key.getKeyCode()) + " ");
                break;
            default:
                s.append("unknown-event-type ");
                break;
        }

        return s.toString();
    }
//    public static String getKeyCode(String keyString) {
////
//            case KeyEvent.VK_AMPERSAND: return "AMPERSAND";
//            case KeyEvent.VK_ASTERISK: return "ASTERISK";
//            case KeyEvent.VK_QUOTEDBL: return "QUOTEDBL";
//            case KeyEvent.VK_LESS: return "LESS";
//            case KeyEvent.VK_GREATER: return "GREATER";
//            case KeyEvent.VK_BRACELEFT: return "BRACELEFT";
//            case KeyEvent.VK_BRACERIGHT: return "BRACERIGHT";
//            case KeyEvent.VK_AT: return "AT";
//            case KeyEvent.VK_COLON: return "COLON";
//            case KeyEvent.VK_CIRCUMFLEX: return "CIRCUMFLEX";
//            case KeyEvent.VK_DOLLAR: return "DOLLAR";
//            case KeyEvent.VK_EURO_SIGN: return "EURO_SIGN";
//            case KeyEvent.VK_EXCLAMATION_MARK: return "EXCLAMATION_MARK";
//            case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
//                return "INVERTED_EXCLAMATION_MARK";
//            case KeyEvent.VK_LEFT_PARENTHESIS: return "LEFT_PARENTHESIS";
//            case KeyEvent.VK_NUMBER_SIGN: return "NUMBER_SIGN";
//            case KeyEvent.VK_MINUS: return "MINUS";
//            case KeyEvent.VK_PLUS: return "PLUS";
//            case KeyEvent.VK_RIGHT_PARENTHESIS: return "RIGHT_PARENTHESIS";
//            case KeyEvent.VK_UNDERSCORE: return "UNDERSCORE";
//
//            case KeyEvent.VK_FINAL: return "FINAL";
//            case KeyEvent.VK_CONVERT: return "CONVERT";
//            case KeyEvent.VK_NONCONVERT: return "NONCONVERT";
//            case KeyEvent.VK_ACCEPT: return "ACCEPT";
//            case KeyEvent.VK_MODECHANGE: return "MODECHANGE";
//            case KeyEvent.VK_KANA: return "KANA";
//            case KeyEvent.VK_KANJI: return "KANJI";
//            case KeyEvent.VK_ALPHANUMERIC: return "ALPHANUMERIC";
//            case KeyEvent.VK_KATAKANA: return "KATAKANA";
//            case KeyEvent.VK_HIRAGANA: return "HIRAGANA";
//            case KeyEvent.VK_FULL_WIDTH: return "FULL_WIDTH";
//            case KeyEvent.VK_HALF_WIDTH: return "HALF_WIDTH";
//            case KeyEvent.VK_ROMAN_CHARACTERS: return "ROMAN_CHARACTERS";
//            case KeyEvent.VK_ALL_CANDIDATES: return "ALL_CANDIDATES";
//            case KeyEvent.VK_PREVIOUS_CANDIDATE: return "PREVIOUS_CANDIDATE";
//            case KeyEvent.VK_CODE_INPUT: return "CODE_INPUT";
//            case KeyEvent.VK_JAPANESE_KATAKANA: return "JAPANESE_KATAKANA";
//            case KeyEvent.VK_JAPANESE_HIRAGANA: return "JAPANESE_HIRAGANA";
//            case KeyEvent.VK_JAPANESE_ROMAN: return "JAPANESE_ROMAN";
//            case KeyEvent.VK_KANA_LOCK: return "KANA_LOCK";
//            case KeyEvent.VK_INPUT_METHOD_ON_OFF: return "INPUT_METHOD_ON_OFF";
//
//            case KeyEvent.VK_AGAIN: return "AGAIN";
//            case KeyEvent.VK_UNDO: return "UNDO";
//            case KeyEvent.VK_COPY: return "COPY";
//            case KeyEvent.VK_PASTE: return "PASTE";
//            case KeyEvent.VK_CUT: return "CUT";
//            case KeyEvent.VK_FIND: return "FIND";
//            case KeyEvent.VK_PROPS: return "PROPS";
//            case KeyEvent.VK_STOP: return "STOP";
//
//            case KeyEvent.VK_COMPOSE: return "COMPOSE";
//            case KeyEvent.VK_ALT_GRAPH: return "ALT_GRAPH";
//        }
//
//        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
//            char c = (char)(keyCode - KeyEvent.VK_NUMPAD0 + '0');
//            return "NUMPAD"+c;
//        }
//
//        return "unknown(0x" + Integer.toString(keyCode, 16) + ")";
//    }

    public static String getKeyText(int keyCode) {
        if (keyCode >= KeyEvent.VK_0 && keyCode <= KeyEvent.VK_9 ||
                keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z) {
            return String.valueOf((char)keyCode);
        }

        switch(keyCode) {
            case KeyEvent.VK_COMMA: return "COMMA";
            case KeyEvent.VK_PERIOD: return "PERIOD";
            case KeyEvent.VK_SLASH: return "SLASH";
            case KeyEvent.VK_SEMICOLON: return "SEMICOLON";
            case KeyEvent.VK_EQUALS: return "EQUALS";
            case KeyEvent.VK_OPEN_BRACKET: return "OPEN_BRACKET";
            case KeyEvent.VK_BACK_SLASH: return "BACK_SLASH";
            case KeyEvent.VK_CLOSE_BRACKET: return "CLOSE_BRACKET";

            case KeyEvent.VK_ENTER: return "ENTER";
            case KeyEvent.VK_BACK_SPACE: return "BACK_SPACE";
            case KeyEvent.VK_TAB: return "TAB";
            case KeyEvent.VK_CANCEL: return "CANCEL";
            case KeyEvent.VK_CLEAR: return "CLEAR";
            case KeyEvent.VK_SHIFT: return "SHIFT";
            case KeyEvent.VK_CONTROL: return "CONTROL";
            case KeyEvent.VK_ALT: return "ALT";
            case KeyEvent.VK_PAUSE: return "PAUSE";
            case KeyEvent.VK_CAPS_LOCK: return "CAPS_LOCK";
            case KeyEvent.VK_ESCAPE: return "ESCAPE";
            case KeyEvent.VK_SPACE: return "SPACE";
            case KeyEvent.VK_PAGE_UP: return "PAGE_UP";
            case KeyEvent.VK_PAGE_DOWN: return "PAGE_DOWN";
            case KeyEvent.VK_END: return "END";
            case KeyEvent.VK_HOME: return "HOME";
            case KeyEvent.VK_LEFT: return "LEFT";
            case KeyEvent.VK_UP: return "UP";
            case KeyEvent.VK_RIGHT: return "RIGHT";
            case KeyEvent.VK_DOWN: return "DOWN";

            // numpad numeric keys handled below
            case KeyEvent.VK_MULTIPLY: return "MULTIPLY";
            case KeyEvent.VK_ADD: return "ADD";
            case KeyEvent.VK_SEPARATOR: return "SEPARATOR";
            case KeyEvent.VK_SUBTRACT: return "SUBTRACT";
            case KeyEvent.VK_DECIMAL: return "DECIMAL";
            case KeyEvent.VK_DIVIDE: return "DIVIDE";
            case KeyEvent.VK_DELETE: return "DELETE";
            case KeyEvent.VK_NUM_LOCK: return "NUM_LOCK";
            case KeyEvent.VK_SCROLL_LOCK: return "SCROLL_LOCK";

            case KeyEvent.VK_F1: return "F1";
            case KeyEvent.VK_F2: return "F2";
            case KeyEvent.VK_F3: return "F3";
            case KeyEvent.VK_F4: return "F4";
            case KeyEvent.VK_F5: return "F5";
            case KeyEvent.VK_F6: return "F6";
            case KeyEvent.VK_F7: return "F7";
            case KeyEvent.VK_F8: return "F8";
            case KeyEvent.VK_F9: return "F9";
            case KeyEvent.VK_F10: return "F10";
            case KeyEvent.VK_F11: return "F11";
            case KeyEvent.VK_F12: return "F12";
            case KeyEvent.VK_F13: return "F13";
            case KeyEvent.VK_F14: return "F14";
            case KeyEvent.VK_F15: return "F15";
            case KeyEvent.VK_F16: return "F16";
            case KeyEvent.VK_F17: return "F17";
            case KeyEvent.VK_F18: return "F18";
            case KeyEvent.VK_F19: return "F19";
            case KeyEvent.VK_F20: return "F20";
            case KeyEvent.VK_F21: return "F21";
            case KeyEvent.VK_F22: return "F22";
            case KeyEvent.VK_F23: return "F23";
            case KeyEvent.VK_F24: return "F24";

            case KeyEvent.VK_PRINTSCREEN: return "PRINTSCREEN";
            case KeyEvent.VK_INSERT: return "INSERT";
            case KeyEvent.VK_HELP: return "HELP";
            case KeyEvent.VK_META: return "META";
            case KeyEvent.VK_BACK_QUOTE: return "BACK_QUOTE";
            case KeyEvent.VK_QUOTE: return "QUOTE";

            case KeyEvent.VK_KP_UP: return "KP_UP";
            case KeyEvent.VK_KP_DOWN: return "KP_DOWN";
            case KeyEvent.VK_KP_LEFT: return "KP_LEFT";
            case KeyEvent.VK_KP_RIGHT: return "KP_RIGHT";

            case KeyEvent.VK_DEAD_GRAVE: return "DEAD_GRAVE";
            case KeyEvent.VK_DEAD_ACUTE: return "DEAD_ACUTE";
            case KeyEvent.VK_DEAD_CIRCUMFLEX: return "DEAD_CIRCUMFLEX";
            case KeyEvent.VK_DEAD_TILDE: return "DEAD_TILDE";
            case KeyEvent.VK_DEAD_MACRON: return "DEAD_MACRON";
            case KeyEvent.VK_DEAD_BREVE: return "DEAD_BREVE";
            case KeyEvent.VK_DEAD_ABOVEDOT: return "DEAD_ABOVEDOT";
            case KeyEvent.VK_DEAD_DIAERESIS: return "DEAD_DIAERESIS";
            case KeyEvent.VK_DEAD_ABOVERING: return "DEAD_ABOVERING";
            case KeyEvent.VK_DEAD_DOUBLEACUTE: return "DEAD_DOUBLEACUTE";
            case KeyEvent.VK_DEAD_CARON: return "DEAD_CARON";
            case KeyEvent.VK_DEAD_CEDILLA: return "DEAD_CEDILLA";
            case KeyEvent.VK_DEAD_OGONEK: return "DEAD_OGONEK";
            case KeyEvent.VK_DEAD_IOTA: return "DEAD_IOTA";
            case KeyEvent.VK_DEAD_VOICED_SOUND: return "DEAD_VOICED_SOUND";
            case KeyEvent.VK_DEAD_SEMIVOICED_SOUND: return "DEAD_SEMIVOICED_SOUND";

            case KeyEvent.VK_AMPERSAND: return "AMPERSAND";
            case KeyEvent.VK_ASTERISK: return "ASTERISK";
            case KeyEvent.VK_QUOTEDBL: return "QUOTEDBL";
            case KeyEvent.VK_LESS: return "LESS";
            case KeyEvent.VK_GREATER: return "GREATER";
            case KeyEvent.VK_BRACELEFT: return "BRACELEFT";
            case KeyEvent.VK_BRACERIGHT: return "BRACERIGHT";
            case KeyEvent.VK_AT: return "AT";
            case KeyEvent.VK_COLON: return "COLON";
            case KeyEvent.VK_CIRCUMFLEX: return "CIRCUMFLEX";
            case KeyEvent.VK_DOLLAR: return "DOLLAR";
            case KeyEvent.VK_EURO_SIGN: return "EURO_SIGN";
            case KeyEvent.VK_EXCLAMATION_MARK: return "EXCLAMATION_MARK";
            case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
                return "INVERTED_EXCLAMATION_MARK";
            case KeyEvent.VK_LEFT_PARENTHESIS: return "LEFT_PARENTHESIS";
            case KeyEvent.VK_NUMBER_SIGN: return "NUMBER_SIGN";
            case KeyEvent.VK_MINUS: return "MINUS";
            case KeyEvent.VK_PLUS: return "PLUS";
            case KeyEvent.VK_RIGHT_PARENTHESIS: return "RIGHT_PARENTHESIS";
            case KeyEvent.VK_UNDERSCORE: return "UNDERSCORE";

            case KeyEvent.VK_FINAL: return "FINAL";
            case KeyEvent.VK_CONVERT: return "CONVERT";
            case KeyEvent.VK_NONCONVERT: return "NONCONVERT";
            case KeyEvent.VK_ACCEPT: return "ACCEPT";
            case KeyEvent.VK_MODECHANGE: return "MODECHANGE";
            case KeyEvent.VK_KANA: return "KANA";
            case KeyEvent.VK_KANJI: return "KANJI";
            case KeyEvent.VK_ALPHANUMERIC: return "ALPHANUMERIC";
            case KeyEvent.VK_KATAKANA: return "KATAKANA";
            case KeyEvent.VK_HIRAGANA: return "HIRAGANA";
            case KeyEvent.VK_FULL_WIDTH: return "FULL_WIDTH";
            case KeyEvent.VK_HALF_WIDTH: return "HALF_WIDTH";
            case KeyEvent.VK_ROMAN_CHARACTERS: return "ROMAN_CHARACTERS";
            case KeyEvent.VK_ALL_CANDIDATES: return "ALL_CANDIDATES";
            case KeyEvent.VK_PREVIOUS_CANDIDATE: return "PREVIOUS_CANDIDATE";
            case KeyEvent.VK_CODE_INPUT: return "CODE_INPUT";
            case KeyEvent.VK_JAPANESE_KATAKANA: return "JAPANESE_KATAKANA";
            case KeyEvent.VK_JAPANESE_HIRAGANA: return "JAPANESE_HIRAGANA";
            case KeyEvent.VK_JAPANESE_ROMAN: return "JAPANESE_ROMAN";
            case KeyEvent.VK_KANA_LOCK: return "KANA_LOCK";
            case KeyEvent.VK_INPUT_METHOD_ON_OFF: return "INPUT_METHOD_ON_OFF";

            case KeyEvent.VK_AGAIN: return "AGAIN";
            case KeyEvent.VK_UNDO: return "UNDO";
            case KeyEvent.VK_COPY: return "COPY";
            case KeyEvent.VK_PASTE: return "PASTE";
            case KeyEvent.VK_CUT: return "CUT";
            case KeyEvent.VK_FIND: return "FIND";
            case KeyEvent.VK_PROPS: return "PROPS";
            case KeyEvent.VK_STOP: return "STOP";

            case KeyEvent.VK_COMPOSE: return "COMPOSE";
            case KeyEvent.VK_ALT_GRAPH: return "ALT_GRAPH";
        }

        if (keyCode >= KeyEvent.VK_NUMPAD0 && keyCode <= KeyEvent.VK_NUMPAD9) {
            char c = (char)(keyCode - KeyEvent.VK_NUMPAD0 + '0');
            return "NUMPAD"+c;
        }

        return "unknown(0x" + Integer.toString(keyCode, 16) + ")";
    }
}
