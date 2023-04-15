package com.example.qmartapp.homePage.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MenuItemDecorator :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            this.left = 16
            this.bottom = 16
            if (position < 4) {
                this.top = 24
            } else {
                this.top = 0
            }
        }
    }
}