package com.hackerstreet.stemup.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hackerstreet.stemup.databinding.*
import com.hackerstreet.stemup.utils.RecyclerUtils
import com.hackerstreet.stemup.utils.RecyclerUtils.gone
import com.hackerstreet.stemup.utils.RecyclerUtils.visible

class LevelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val matrix = RecyclerUtils.matrix
    private val list = ArrayList<Pair<Int, Int>>()
    private lateinit var listener: ((Int) -> Unit)

    override fun getItemViewType(position: Int): Int {
        return matrix[position]
    }


    inner class ViewHolder1(private val view: ItemType1Binding) : ViewHolder(view.root) {
        fun bind() {
            val item = list[bindingAdapterPosition]
            if (item.second == 1) {
                view.apply {
                    completeCoin.visible()
                    lockCoin.gone()
                }
            } else {
                view.apply {
                    completeCoin.gone()
                    lockCoin.visible()
                }
            }
            view.completeCoin.setOnClickListener { listener.invoke(item.first) }
        }
    }

    inner class ViewHolder2(private val view: ItemType2Binding) : ViewHolder(view.root) {
        fun bind() {
            val item = list[bindingAdapterPosition]
            if (item.second == 1) {
                view.apply {
                    completeCoin.visible()
                    lockCoin.gone()
                }
            } else {
                view.apply {
                    completeCoin.gone()
                    lockCoin.visible()
                }
            }
            view.completeCoin.setOnClickListener { listener.invoke(item.first) }

        }
    }

    inner class ViewHolder3(private val view: ItemType3Binding) : ViewHolder(view.root) {
        fun bind() {
            val item = list[bindingAdapterPosition]
            if (item.second == 1) {
                view.apply {
                    completeCoin.visible()
                    lockCoin.gone()
                }
            } else {
                view.apply {
                    completeCoin.gone()
                    lockCoin.visible()
                }
            }
            view.completeCoin.setOnClickListener { listener.invoke(item.first) }


        }
    }

    inner class ViewHolder4(private val view: ItemType4Binding) : ViewHolder(view.root) {
        fun bind() {
            val item = list[bindingAdapterPosition]
            if (item.second == 1) {
                view.apply {
                    completeCoin.visible()
                    lockCoin.gone()
                }
            } else {
                view.apply {
                    completeCoin.gone()
                    lockCoin.visible()
                }
            }

            view.completeCoin.setOnClickListener { listener.invoke(item.first) }


        }
    }

    inner class ViewHolderHeader(private val view: ItemHeaderBinding) : ViewHolder(view.root) {
        fun bind() {
            val item = list[bindingAdapterPosition].first
            if (item < 100) view.headerText.text = "Lesson " + (item % 10).toString()
            else if (item in 100..199) view.headerText.text =
                "Lesson " + (10 + item % 10).toString()
            else if (item in 200..299) view.headerText.text =
                "Lesson " + (20 + item % 10).toString()
            else view.headerText.text = "Lesson " + (30 + item % 10).toString()
        }
    }

    override fun getItemCount(): Int = matrix.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            1 -> {
                return ViewHolder1(
                    ItemType1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            2 -> {
                return ViewHolder2(
                    ItemType2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            3 -> {
                return ViewHolder3(
                    ItemType3Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            4 -> {
                return ViewHolder4(
                    ItemType4Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return ViewHolderHeader(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> (holder as ViewHolder1).bind()
            2 -> (holder as ViewHolder2).bind()
            3 -> (holder as ViewHolder3).bind()
            4 -> (holder as ViewHolder4).bind()
            else -> (holder as ViewHolderHeader).bind()
        }
    }

    fun submitList(array: List<Pair<Int, Int>>) {
        list.clear()
        list.addAll(array)
        notifyDataSetChanged()
    }

    fun initClickListener(l: ((Int) -> Unit)) {
        listener = l
    }

}