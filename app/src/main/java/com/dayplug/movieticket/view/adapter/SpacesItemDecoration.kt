package com.dayplug.movieticket.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(spcae : Int) : RecyclerView.ItemDecoration() {
     val space : Int = spcae

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.right = space
    }
}