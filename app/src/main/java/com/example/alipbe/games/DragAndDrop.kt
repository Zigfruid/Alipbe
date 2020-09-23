package com.example.alipbe.games

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.alipbe.R
import kotlinx.android.synthetic.main.fragment_letters.*
import kotlinx.android.synthetic.main.game_menu_fragment.*
import kotlinx.android.synthetic.main.test_fragment.*

class DragAndDrop : Fragment(R.layout.test_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        llTop.setOnDragListener(dragListener())
//
//
//        dragView.setOnLongClickListener {
//            val clipText = "This is America"
//            val item = ClipData.Item(clipText)
//            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
//            val data = ClipData(clipText, mimeTypes, item)
//            val dragShadowBuilder = View.DragShadowBuilder(it)
//            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
//            it.visibility = View.GONE
//            true
//
//        }
    }

}