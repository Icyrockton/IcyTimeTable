package com.icyrockton.icytimetablesample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icyrockton.icytimetable.IcyColInfoDecoration
import com.icyrockton.icytimetable.IcyRowTimeInfoDecoration
import com.icyrockton.icytimetable.IcyTimeTableHelper
import com.icyrockton.icytimetable.IcyTimeTableManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val totalCoursePerDay = 14
        val columnCount = 7
        val gapFilling = IcyTimeTableHelper.gapFilling(data, totalCoursePerDay, columnCount)
        val icyRowInfo = IcyTimeTableHelper.getIcyRowInfo(gapFilling)



        val adapter = GroupAdapter<GroupieViewHolder>()
        recycler_view.addItemDecoration(
            MyRowInfoDecoration(
                resources.getDimensionPixelSize(R.dimen.paddingLeft),
                resources.getDimensionPixelSize(R.dimen.perCourseHeight),
                Color.BLACK,
                resources.getDimension(R.dimen.textSize),
                resources.getDimension(R.dimen.textSize)
                ,
                icyRowInfo,
                totalCoursePerDay
            )
        )
        recycler_view.addItemDecoration(
            MyColInfoDecoration(columnCount,resources.getDimensionPixelSize(R.dimen.paddingTop),Color.GRAY,Color.WHITE,Color.BLUE,
                resources.getDimension(R.dimen.textSize))
        )
        recycler_view.layoutManager = IcyTimeTableManager(
            45,
            resources.getDimensionPixelSize(R.dimen.perCourseHeight),
            columnCount,
            totalCoursePerDay
        ) {
            gapFilling[it]
        }
        recycler_view.adapter = adapter
        gapFilling.map {
            when (it) {
                is IcyTimeTableManager.EmptyCourseInfo -> SpaceItem()
                is MyCourse -> CourseItem(it)
                else -> SpaceItem()
            }
        }.let(adapter::update)

    }
}