package ir.ah.vajehyabfarsi.other.util

import java.text.SimpleDateFormat
import java.util.*


fun dateToName(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return dateFormat.format(date.toLocaleString())
}

fun filterNameToFilterId(filter: String): String {
    var a = filter
    val pNum =
        arrayOf("dehkhoda,moein,amid,motaradef,farhangestan,sareh,ganjvajeh,wiki,slang,quran,name,thesis,isfahani,bakhtiari,tehrani,dezfuli,gonabadi,mazani,en2fa,ar2fa,fa2en,fa2ar"
            , "dehkhoda", "moein",
            "amid", "motaradef", "farhangestan",
            " sareh", " ganjvajeh", " wiki",
            " slang", " quran", " name", " thesis",
            " isfahani", " bakhtiari", " tehrani",
            " dezfuli", " gonabadi", " mazani",
            " en2fa", " ar2fa", " fa2en", " fa2ar",)

    a = a.replace("همه", pNum[0])
    a = a.replace("لغتنامهٔ دهخدا", pNum[1])
    a = a.replace("فرهنگ فارسی معین", pNum[2])
    a = a.replace("فرهنگ فارسی عمید", pNum[3])
    a = a.replace("واژگان مترادف و متضاد", pNum[4])
    a = a.replace("فرهنگ واژه های مصوّب فرهنگستان", pNum[5])
    a = a.replace("واژه های فارسی سره", pNum[6])
    a = a.replace("فرهنگ گنج واژه", pNum[7])
    a = a.replace("واژه نامهٔ آزاد", pNum[8])
    a = a.replace("اصطلاحات عامیانه", pNum[9])
    a = a.replace("فرهنگ واژگان قرآن", pNum[10])
    a = a.replace("فرهنگ نام ها", pNum[11])
    a = a.replace("فرهنگ لغات علمی", pNum[12])
    a = a.replace("لهجه و گویش اصفهانی", pNum[13])
    a = a.replace("لهجه و گویش بختیاری", pNum[14])
    a = a.replace("لهجه و گویش تهرانی", pNum[15])
    a = a.replace("لهجه و گویش دزفولی", pNum[16])
    a = a.replace("لهجه و گویش گنابادی", pNum[17])
    a = a.replace("لهجه و گویش مازنی", pNum[18])
    a = a.replace("دیکشنری انگلیسی به فارسی", pNum[19])
    a = a.replace("دیکشنری عربی به فارسی", pNum[20])
    a = a.replace("دیکشنری فارسی به انگلیسی", pNum[21])
    a = a.replace("دیکشنری فارسی به عربی", pNum[22])

    return a
}