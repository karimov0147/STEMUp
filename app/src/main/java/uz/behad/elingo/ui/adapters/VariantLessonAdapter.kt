package uz.behad.elingo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.behad.elingo.R
import uz.behad.elingo.databinding.ItemLessonQuizBinding

class VariantLessonAdapter : RecyclerView.Adapter<VariantLessonAdapter.ViewHolder>() {
    private val list = ArrayList<Pair<String, Boolean>>()
    private lateinit var listener: ((String) -> Unit)

    inner class ViewHolder(private val view: ItemLessonQuizBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind() {
            val item = list[bindingAdapterPosition]
            view.root.text = item.first
            if (item.second) {
                view.root.setBackgroundResource(R.drawable.recycler_bg_selected)
            } else {
                view.root.setBackgroundResource(R.drawable.recycler_bg)
            }

            view.root.setOnClickListener { listener.invoke(item.first); onClicked(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemLessonQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    fun submitList(array: List<String>) {
        list.clear()
        list.addAll(array.map { Pair(it, false) })
        notifyDataSetChanged()
    }

    fun initClickListener(l: (String) -> Unit) {
        listener = l
    }

    fun onClicked(item: Pair<String, Boolean>) {
        for (i in list.indices) {
            if (list[i] == item) {
                list[i] = Pair(list[i].first, true)
            } else {
                list[i] = Pair(list[i].first, false)
            }
        }
        notifyDataSetChanged()

    }

}