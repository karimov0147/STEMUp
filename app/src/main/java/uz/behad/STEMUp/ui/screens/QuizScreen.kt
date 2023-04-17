package uz.behad.STEMUp.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.behad.STEMUp.R
import uz.behad.STEMUp.data.local.DictEntity
import uz.behad.STEMUp.data.storage.LocaleStorage
import uz.behad.STEMUp.databinding.DialogCorrectBinding
import uz.behad.STEMUp.databinding.DialogWrongBinding
import uz.behad.STEMUp.databinding.ScreenQuizBinding
import uz.behad.STEMUp.ui.adapters.VariantsAdapter
import uz.behad.STEMUp.ui.presenters.QuizViewModel

class QuizScreen : Fragment(R.layout.screen_quiz) {

    private val binding by viewBinding(ScreenQuizBinding::bind)
    private val viewModel by viewModels<QuizViewModel>()
    private lateinit var answer: String
    private lateinit var description: String
    private var selectedAnswer: String? = null
    private val adapter by lazy { VariantsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pointsLiveData.observe(viewLifecycleOwner, trackPointObserver)
        viewModel.variantsLiveData.observe(viewLifecycleOwner, loadQuestionObserver)
        viewModel.getQuestion()
        binding.tvOptions.adapter = adapter
        binding.tvPoints.text = LocaleStorage.getPoints().toString()
        binding.ivClose.setOnClickListener { requireActivity().onBackPressed() }
        adapter.initClickListener {
            selectedAnswer = it
        }
        binding.tvContinue.setOnClickListener {
            if (selectedAnswer != null) checkAnswer(selectedAnswer ?: return@setOnClickListener)
            else Toast.makeText(requireContext(), "select an answer", Toast.LENGTH_SHORT).show()
        }
    }

    private val loadQuestionObserver = Observer<List<DictEntity>> {
        generateQuestion(it)
    }
    private val trackPointObserver = Observer<Int> {
        binding.tvPoints.text = it.toString()
    }

    private fun generateQuestion(list: List<DictEntity>) {
        answer = list[0].term
        binding.tvTitle.text = list[0].description
        description = list[0].description
        adapter.submitList(list.map { it.term }.shuffled())
    }

    private fun checkAnswer(str: String) {
        if (answer == str) {
            val dialog = BottomSheetDialog(requireContext())
            val view = View.inflate(requireContext(), R.layout.dialog_correct, null)
            val dBind = DialogCorrectBinding.bind(view)
            dialog.setContentView(view)
            dialog.create()
            dBind.tvContinue.setOnClickListener {
                dialog.dismiss()
                viewModel.getQuestion()
                viewModel.setPoints()
            }
            dBind.ivShare.setOnClickListener {
                val intent2: Intent = Intent()
                intent2.setAction(Intent.ACTION_SEND)
                intent2.type = "text/plain"
                intent2.putExtra(
                    Intent.EXTRA_TEXT,
                    "$answer \n\n $description \n\nPowered by STEMUp"
                )
                startActivity(Intent.createChooser(intent2, "Share via"))

            }
            dialog.show()
        } else {
            val dialog = BottomSheetDialog(requireContext())
            val view = View.inflate(requireContext(), R.layout.dialog_wrong, null)
            val dBind = DialogWrongBinding.bind(view)
            dialog.setContentView(view)
            dialog.create()
            dBind.CorrectAnswerTv.text = answer
            dBind.ivShare.setOnClickListener {
                val intent2: Intent = Intent()
                intent2.setAction(Intent.ACTION_SEND)
                intent2.type = "text/plain"
                intent2.putExtra(
                    Intent.EXTRA_TEXT,
                    "$answer \n\n $description \n\nPowered by STEMUp"
                )
                startActivity(Intent.createChooser(intent2, "Share via"))

            }
            dBind.tvContinue.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }


}