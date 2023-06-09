package com.hackerstreet.stemup.ui.adapters

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackerstreet.stemup.data.local.DictEntity
import com.hackerstreet.stemup.databinding.ItemDictionaryBinding
import com.hackerstreet.stemup.utils.RecyclerUtils.toColoredSpannable
import me.zhanghai.android.fastscroll.PopupTextProvider

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>(), PopupTextProvider {

    private var cursor: Cursor? = null
    private var query: String? = null
    private lateinit var listener: ((DictEntity) -> Unit)


    inner class ViewHolder(private val view: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(data: DictEntity) {
            view.itemTitle.text = data.term.toColoredSpannable(query.toString())
            view.itemDescription.text =
                data.description.replace("&quot;", "\"").replace("&amp;", "'")
                    .replace("&apos;", "\"")

            view.root.setOnClickListener {
                listener.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemDictionaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!cursor!!.moveToPosition(position)) {
            return
        }

        val data: DictEntity = DictEntity(
            cursor!!.getInt(0),
            cursor!!.getInt(1),
            cursor!!.getString(2),
            cursor!!.getString(3)
        )

        holder.bind(data)
    }

    override fun getItemCount(): Int {
        if (cursor == null) {
            return 0
        } else return cursor!!.count
    }


    fun submitCursor(newCursor: Cursor, query: String = "") {
        this.cursor = newCursor
        this.query = query
        notifyDataSetChanged()
    }

    override fun getPopupText(position: Int): String {
        return cursor?.getString(2)?.substring(0, 1)?.uppercase() ?: return ""
    }

    fun initClickListener(l: ((DictEntity) -> Unit)) {
        listener = l
    }
}