package com.ordinary.basis.common

import java.util.*
import java.util.regex.Pattern

/**
 * @see
 * @author 01218
 * @version 1.0
 * @date 20-2-24
 */
object RegexConstant {

    val REGEX_PHONE:Regex by lazy { Regex("^1([39][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}\$") }
    val REGEX_SQUARE_BRACKETS:Regex by lazy { Regex("^(\\[).*?(])$") }
    val REGEX_PREFIX_URL:Regex by lazy { Regex("^((http|ftp|https)://).*$") }
    val REGEX_CAMEL: Pattern by lazy { Pattern.compile("^[A-Z]$") }


    /**
     * 计算年龄
     */
    fun calculateAge(date: Date?):Int {
        val cal = Calendar.getInstance()
        if (cal.before(date)) { //出生日期晚于当前时间，无法计算
            throw IllegalArgumentException(
                    "出生日期晚于当前时间，无法计算!");
        }
        val yearNow = cal.get(Calendar.YEAR)  //当前年份
        val monthNow = cal.get(Calendar.MONTH)  //当前月份
        val dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH) //当前日期
        cal.time = date
        val yearBirth = cal.get(Calendar.YEAR)
        val monthBirth = cal.get(Calendar.MONTH)
        val dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH)
        var age = yearNow - yearBirth   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            }else{
                age--//当前月份在生日之前，年龄减一

            }
        }
        return age
    }
}