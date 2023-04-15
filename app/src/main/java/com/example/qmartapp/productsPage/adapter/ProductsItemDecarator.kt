package com.example.qmartapp.productsPage.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProductsItemDecorator :
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
            this.right = 8
            this.left = 8
            this.bottom = 24
            if (position < 2) {
                this.top = 60
            } else {
                this.top = 0
            }
        }
    }
}