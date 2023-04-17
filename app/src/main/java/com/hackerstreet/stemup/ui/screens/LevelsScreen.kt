package com.hackerstreet.stemup.ui.screens

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hackerstreet.stemup.R
import com.hackerstreet.stemup.data.storage.LocaleStorage
import com.hackerstreet.stemup.databinding.ScreenLevelsBinding
import com.hackerstreet.stemup.ui.adapters.LevelAdapter
import com.hackerstreet.stemup.ui.presenters.LevelsViewModel
import com.hackerstreet.stemup.utils.RecyclerUtils
import com.hackerstreet.stemup.utils.RecyclerUtils.gone
import com.hackerstreet.stemup.utils.RecyclerUtils.visible

class LevelsScreen : Fragment(R.layout.screen_levels) {
    private val binding by viewBinding(ScreenLevelsBinding::bind)
    private val viewModel by viewModels<LevelsViewModel>()
//    private lateinit var adapter : LevelAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.roadMapLiveData.observe(viewLifecycleOwner, roadMapObserver)
        viewModel.getRoadMap()
    }

    private val roadMapObserver = Observer<List<Pair<Int, Int>>> {
        val adapter = LevelAdapter()
        binding.recyclerView.adapter = adapter
        adapter.submitList(it)
        adapter.initClickListener {
            RecyclerUtils.lessonId = it
            requireActivity().findNavController(R.id.navHostFragment)
                .navigate(R.id.action_rootScreen_to_lesson_navigation)
        }
    }

    private fun initViews() {
        binding.categoryTv.setOnClickListener {
            binding.apply {
                if (categoryLayout.visibility == View.VISIBLE) {
                    categoryLayout.gone()
                } else {
                    categoryLayout.visible()
                    setBackground(LocaleStorage.getCategory())
                }
            }
        }
        binding.scienceLayout.setOnClickListener {
            setBackground(1)
            binding.categoryLayout.gone()
            viewModel.getRoadMap()
        }
        binding.mathLayout.setOnClickListener {
            setBackground(4)
            binding.categoryLayout.gone()
            viewModel.getRoadMap()
        }
        binding.engineLayout.setOnClickListener {
            setBackground(3)
            binding.categoryLayout.gone()
            viewModel.getRoadMap()
        }
        binding.techLayout.setOnClickListener {
            setBackground(2)
            binding.categoryLayout.gone()
            viewModel.getRoadMap()
        }
        binding.diamondsTv.text = (LocaleStorage.getPoints().toString())
        setBackground(LocaleStorage.getCategory())

    }

    private fun setBackground(state: Int) {
        when (state) {
            1 -> {
                binding.ivCategoryScience.setBackgroundResource(R.drawable.recycler_bg_selected)
                binding.ivCategoryTechnology.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryEngineering.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryMath.setBackgroundResource(R.drawable.recycler_bg)

                binding.tvCategoryScience.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                binding.tvCategoryTechnology.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryEngineering.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryMath.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                LocaleStorage.setCategory(1)
                binding.categoryTv.apply {
                    setText(R.string.science)
                    setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_sciense,
                        0,
                        R.drawable.ic_down,
                        0
                    )
                }

            }
            2 -> {

                binding.ivCategoryScience.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryTechnology.setBackgroundResource(R.drawable.recycler_bg_selected)
                binding.ivCategoryEngineering.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryMath.setBackgroundResource(R.drawable.recycler_bg)

                binding.tvCategoryScience.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryTechnology.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                binding.tvCategoryEngineering.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryMath.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                LocaleStorage.setCategory(2)
                binding.categoryTv.apply {
                    setText(R.string.technology)
                    setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_technology,
                        0,
                        R.drawable.ic_down,
                        0
                    )
                }
            }
            3 -> {

                binding.ivCategoryScience.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryTechnology.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryEngineering.setBackgroundResource(R.drawable.recycler_bg_selected)
                binding.ivCategoryMath.setBackgroundResource(R.drawable.recycler_bg)

                binding.tvCategoryScience.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryTechnology.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryEngineering.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
                binding.tvCategoryMath.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                LocaleStorage.setCategory(3)
                binding.categoryTv.apply {
                    setText(R.string.engineering)
                    setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_engineering,
                        0,
                        R.drawable.ic_down,
                        0
                    )
                }
            }
            4 -> {

                binding.ivCategoryScience.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryTechnology.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryEngineering.setBackgroundResource(R.drawable.recycler_bg)
                binding.ivCategoryMath.setBackgroundResource(R.drawable.recycler_bg_selected)

                binding.tvCategoryScience.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryTechnology.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryEngineering.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tvCategoryMath.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )

                LocaleStorage.setCategory(4)
                binding.categoryTv.apply {
                    setText(R.string.math)
                    setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_math,
                        0,
                        R.drawable.ic_down,
                        0
                    )
                }
            }
        }
    }
}