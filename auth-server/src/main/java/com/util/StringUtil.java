package com.util;

import java.util.Vector;

public class StringUtil {
	
	/**
	 * 判断字符串是否为空
	 * @by 张金昕
	 * @param str
	 * @return
	 */
	 public static boolean isBlank(String str) {
        int strLen;
        if(str == null || (strLen = str.length()) == 0)
            return true;
        for(int i = 0; i < strLen; i++)
            if(!Character.isWhitespace(str.charAt(i)))
                return false;
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
	 * 以type为分界符分离str字符串,返回分离后的数组
	 * 
	 * @param str
	 *            待处理字符串
	 * @param type
	 *            分界符

	 * @return array 处理后的字符串数组

	 */
	public static String[] split(String str, String type) {

		int begin = 0;
		int pos = 0;
		String tempstr = "";
		String[] array = null;
		Vector<String> vec = null;
		int len = str.trim().length();
		str = str + type;

		if (len > 0) {
			while (str.length() > 0) {

				if (vec == null) {
					vec = new Vector<String>();
				}

				pos = str.indexOf(type, begin);
				tempstr = str.substring(begin, pos);
				str = str.substring(pos + 1, str.length());
				vec.add(tempstr);
			}
		} else {
			vec = new Vector<String>();
			vec.add("");
		}
		if (vec != null) {
			array = new String[vec.size()];

			for (int i = 0; i < vec.size(); i++) {
				array[i] = (String) vec.get(i);
			}
		} else {
			array = new String[0];
		}

		return array;
	}
}
