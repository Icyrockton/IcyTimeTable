package com.icyrockton.icytimetablesample

import com.icyrockton.icytimetable.IcyRowTimeInfoDecoration

class MyRowInfoDecoration(
    width: Int,
    perCourseHeight: Int,
    textColor: Int,
    numberTextSize: Float,
    timeTextSize: Float,
    rowNumberList: List<IcyRowInfo>,
    totalCoursePerDay: Int
) : IcyRowTimeInfoDecoration(
    width,
    perCourseHeight,
    textColor,
    numberTextSize,
    timeTextSize,
    rowNumberList,
    totalCoursePerDay
) {
    override fun getStartTime(rowNumber: Int): String {
       return when(rowNumber){
           1->"08:00"
           2->"09:00"
           3->"11:00"
           4->"12:00"
           5->"14:20"
           6->"15:20"
           else->"00:00"
       }
    }

    override fun getEndTime(rowNumber: Int): String {
        return when(rowNumber){
            1->"08:50"
            2->"09:50"
            3->"11:10"
            4->"13:30"
            5->"15:00"
            6->"15:50"
            else->"00:00"
        }
    }
}