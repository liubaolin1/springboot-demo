package com.example.utils.common;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.utils.common.Print.println;

/**
 * @Description:
 * @author: liubao
 * @Date: Created in 2018/6/14 16:36
 */
public class StringUtils {
    /**
     * 可以同时传入多个参数，如果其中有一个为 ""或null 则返回true
     * 多个参数时 只要有一个为空 返回true
     * @param strings
     * @return
     */
    public static boolean isEmpty(String... strings){
        if(strings != null && strings.length>0) {
            for (String o : strings) {
                if (o == null || o.length() == 0 || "".equals(o)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 空串""
     */
    public final static String EMPTY_STR = "";
    public static final String CELLPHONE = "^[1][0-9]{10}$";
    public static final String VALIDPASSWORD = "^[\\w]{6,16}$";
    public static final String CHINESE = "[\u0391-\uFFE5]+$";
    public static final String D_D_$ = "^[-\\+]?\\d+\\.\\d+$";
    public static final String W_W_W_W_W_W_$ = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    public static final String D_$ = "^[-\\+]?[\\d]+$";
    public static final String WWW = "[\\w]*";
    public static final String MOBILE = "^1[\\d]{10}$";

    /**
     * 判断是否是空字符串 null,""和" " 都返回 true
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0 || "NULL".equals(str.toUpperCase());
    }

    public static boolean isCellPhone(String str) {
        Pattern p = Pattern.compile(CELLPHONE);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 校验密码只包含数字字母下划线，且6-16位长度
     *
     * @param str
     * @return
     */
    public static boolean validPassword(String str) {
        Pattern p = Pattern.compile(VALIDPASSWORD);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断字符串不是空字符串，并且不是null
     * 如果不是空字符串，返回true
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        if (str == null || "null".equals(str.toLowerCase()) || str.trim().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证字符长度,按字符单位,以utf-8编码
     *
     * @param str 验证字符串
     * @param min 最小长度,可以为空
     * @param max 最大长度,可以为空
     * @return 是否符合条件
     */
    public static boolean checkLength(String str, Integer min, Integer max) {
        int strLen = getWordCountCode(str, "UTF-8");
        if (min != null) {
            if (min > strLen) {
                return false;
            }
        }
        if (max != null) {
            if (max < strLen) {
                return false;
            }
        }
        return true;
    }

    /**
     * 按指定编码格式返回字符数
     *
     * @param str  字符串
     * @param code 编码格式
     * @return 长度
     */
    public static int getWordCountCode(String str, String code) {
        try {
            if (isBlank(code)) {
                code = "UTF-8";
            }
            if (str == null) {
                return 0;
            } else {
                return str.getBytes(code).length;
            }
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 判断输入的字符串是否为纯汉字
     */
    public static boolean isChinese(String str) {
        Pattern pattern = Pattern.compile(CHINESE);
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     */
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile(D_D_$);
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串是否符合Email样式
     */
    public static boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern
                .compile(W_W_W_W_W_W_$);
        return pattern.matcher(email).matches();
    }

    /**
     * 判断是否为整数
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile(D_$);
        return pattern.matcher(str).matches();
    }

    /**
     * 验证英数字符，包括下划线。与[A-Za-z0-9_]等效
     */
    public static boolean isLetter(String str) {
        if (str == null || str.length() < 0) {
            return false;
        }
        Pattern pattern = Pattern.compile(WWW);
        return pattern.matcher(str).matches();
    }

    /**
     * 验证是不是合法的手机号码
     */
    public static boolean isMobilePhone(String handset) {
        try {
            Pattern pattern = Pattern.compile(MOBILE);
            Matcher matcher = pattern.matcher(handset);
            return matcher.matches();

        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * 在字符串source中，用to替换from
     */
    public static String replace(String from, String to, String source) {
        if (source == null || from == null || to == null) {
            return null;
        }

        StringBuffer str = new StringBuffer("");
        int index = -1;
        while ((index = source.indexOf(from)) != -1) {
            str.append(source.substring(0, index) + to);
            source = source.substring(index + from.length());
            index = source.indexOf(from);
        }
        str.append(source);
        return str.toString();
    }

    /**
     * 人民币转换成大写
     */
    public static String toBigMoney(String str) {
        double value;
        try {
            value = Double.parseDouble(str.trim());
        } catch (Exception e) {
            return null;
        }
        char[] hunit = {'拾', '佰', '仟'}; // 段内位置表示
        char[] vunit = {'万', '亿'}; // 段名表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        long midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串

        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if ("00".equals(rail)) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角"
                    + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0) {
                prefix += hunit[idx - 1];
            }

            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        // 如果整数部分存在,则有元的字样
        if (prefix.length() > 0) {
            prefix += '元';
        }
        return prefix + suffix; // 返回正确表示
    }

    /**
     * 截取字符串　超出的字符用symbol代替
     *
     * @param len    字符串长度　长度计量单位为一个GBK汉字  两个英文字母计算为一个单位长度
     * @param str
     * @param symbol
     * @return
     */
    public static String getLimitLengthString(String str, int len, String symbol) {
        int iLen = len * 2;
        int counterOfDoubleByte = 0;
        String strRet = EMPTY_STR;
        try {
            if (str != null) {
                byte[] b = str.getBytes("GBK");
                if (b.length <= iLen) {
                    return str;
                }
                for (int i = 0; i < iLen; i++) {
                    if (b[i] < 0) {
                        counterOfDoubleByte++;
                    }
                }
                if (counterOfDoubleByte % 2 == 0) {
                    strRet = new String(b, 0, iLen, "GBK") + symbol;
                    return strRet;
                } else {
                    strRet = new String(b, 0, iLen - 1, "GBK") + symbol;
                    return strRet;
                }
            } else {
                return EMPTY_STR;
            }
        } catch (Exception ex) {
            return str.substring(0, len);
        } finally {
            strRet = null;
        }
    }

    /**
     * 截取字符串　超出的字符用symbol代替
     *
     * @param len    字符串长度　长度计量单位为一个GBK汉字  两个英文字母计算为一个单位长度
     * @param str
     * @return
     */
    public static String getLimitLengthString(String str, int len) {
        return getLimitLengthString(str, len, "...");
    }

    /**
     * 如果是null返回"";
     * 否则，返回原值
     *
     * @param str
     * @return str or ""
     */
    public static String null2Empty(String str) {
        if (str == null) {
            return EMPTY_STR;
        } else {
            return str;
        }
    }

    /**
     * 如果是""返回null;
     * 否则，返回原值
     *
     * @param str
     * @return str or ""
     */
    public static String empty2null(String str) {
        if (EMPTY_STR.equals(str)) {
            return null;
        } else {
            return str;
        }
    }

    /**
     * 用指定字符，补足一个字符串到指定长度,比如字符串补零
     *
     * @param str  - 源字符串
     * @param size - 补足后应达到的长度
     * @return - 补零后的结果
     */
    public static String fillPreChar(final String str, final int size,
                                     final char ch) {
        String result;
        if (str.length() < size) {
            final char[] s = new char[size - str.length()];
            for (int i = 0; i < (size - str.length()); i++) {
                s[i] = ch;
            }
            result = new String(s) + str;
        } else {
            result = str;
        }

        return result;
    }

    /**
     * 替换对应下标的index字符
     *
     * @param str
     * @param index
     * @param ch
     * @return
     */
    public static String replaceCharByIndex(final String str, final int index, final char ch) {
        if (str.length() < index) {
            return str;
        }
        char[] cs = str.toCharArray();
        cs[index] = ch;
        return new String(cs);
    }


    public static boolean isContainsChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    public static String validateNullString(String str) {
        if (null != str && !"".equals(str.trim())) {
            str = str.trim();
        } else {
            str = null;
        }

        return str;
    }

    public static String validateNullString(String str, String defaultVal) {
        if (null != str && !"".equals(str.trim())) {
            str = str.trim();
        } else {
            str = defaultVal;
        }

        return str;
    }

    /**
     * trim Map中字符串类型值的两端空格
     *
     * @param map
     */
    public static Map<String, Object> trimMapStrVal(Map<String, Object> map) {

        if (map == null || map.isEmpty()) {
            return map;
        }

        for (String key : map.keySet()) {
            Object val = map.get(key);
            if (val == null || !(val instanceof String)) {
                continue;
            }
            map.put(key, StringUtils.null2Empty(val.toString()).trim());
        }

        return map;
    }

    /**
     * 隐藏手机号中间4位，只显示@前面的首位和末位
     *
     * @param str
     * @return
     */
    public static String hiddenMiddle(String str) {
        String ss = str;
        if (isCellPhone(str)) {
            ss = str.substring(0, 3) + "****" + str.substring(7);
        } else if (isEmail(str)) {
            ss = str.substring(0, 1) + "****" + str.substring(str.indexOf("@") - 1);
        }
        return ss;

    }

    /**
     * 获取字符串的长度，中英混合字符串
     *
     * @param value
     * @param chineseLength 一个中文代表的长度
     * @return
     */
    public static int length(String value, int chineseLength) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为chineseLength，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度 */
                valueLength += chineseLength;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static void main(String[] args) {
        String source = "abcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
        String from = "efg";
        String to = "替换了";
        System.out.println("在字符串source中，用to替换from，替换结果为："
                + replace(from, to, source));
        System.out.println("返回指定字节长度的字符串："
                + getLimitLengthString("字1符串abcdefgabcd", 2, EMPTY_STR));
        System.out.println("返回指定字节长度的字符串："
                + getLimitLengthString("字1符串abcdefgabcd", 4));
        System.out.println("判断是否为整数：" + isInteger("-1"));
        System.out.println("判断是否为浮点数，包括double和float：" + isDouble("+0.36"));
        System.out.println("判断输入的字符串是否符合Email样式：" + isEmail("fhwbj@163.com"));
        System.out.println("判断输入的字符串是否为纯汉字：" + isChinese("你好！"));
        System.out.println("人民币转换成大写：" + toBigMoney("1020965.00"));
        System.out.println("判断是不是合法的手机号码：" + isMobilePhone("15981807340"));
        System.out.println("判断是不是英数字符，包括下划线：" + isLetter("1k2_"));
        System.out.println("字符前补零：" + fillPreChar("1", 5, '0'));

    }
}
