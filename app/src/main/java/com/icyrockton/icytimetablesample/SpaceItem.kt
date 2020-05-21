package com.icyrockton.icytimetablesample

import android.view.View
import com.icyrockton.icytimetablesample.databinding.ItemSpaceBinding
import com.xwray.groupie.viewbinding.BindableItem

class SpaceItem: BindableItem<ItemSpaceBinding>() {
    override fun getLayout(): Int=R.layout.item_space

    override fun bind(viewBinding: ItemSpaceBinding, position: Int) {
        viewBinding.num=position.toString()
    }

    override fun initializeViewBinding(view: View): ItemSpaceBinding= ItemSpaceBinding.bind(view)
}