package com.icyrockton.icytimetablesample

import android.view.View
import com.icyrockton.icytimetablesample.databinding.ItemCourseBinding
import com.xwray.groupie.viewbinding.BindableItem

class CourseItem(private val data: MyCourse) : BindableItem<ItemCourseBinding>() {
    override fun getLayout(): Int = R.layout.item_course

    override fun bind(viewBinding: ItemCourseBinding, position: Int) {
       viewBinding.num=position.toString()
    }
    override fun initializeViewBinding(view: View): ItemCourseBinding = ItemCourseBinding.bind(view)
}