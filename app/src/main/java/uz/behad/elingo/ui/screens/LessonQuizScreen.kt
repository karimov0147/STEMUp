package uz.behad.elingo.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.behad.elingo.R
import uz.behad.elingo.data.models.LessonModel
import uz.behad.elingo.data.storage.LocaleStorage
import uz.behad.elingo.databinding.DialogCorrectBinding
import uz.behad.elingo.databinding.DialogWrongBinding
import uz.behad.elingo.databinding.ScreenLessonQuizBinding
import uz.behad.elingo.ui.adapters.VariantLessonAdapter
import uz.behad.elingo.utils.RecyclerUtils

class LessonQuizScreen : Fragment(R.layout.screen_lesson_quiz) {
    private val binding by viewBinding(ScreenLessonQuizBinding::bind)
    var counter = 0
    var data = ArrayList<LessonModel>()
    var adapter = VariantLessonAdapter()
    var answer = ""
    var description = ""
    var selectedAnswer = ""

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOptions.adapter = adapter
        data.apply {
            clear()
            addAll(RecyclerUtils.data)
        }
        generateQuestion()
        adapter.initClickListener {
            selectedAnswer = it
        }

        binding.tvContinue.setOnClickListener {
            if (selectedAnswer.isNotEmpty()) checkAnswer(selectedAnswer)
            else Toast.makeText(requireContext(), "select an answer", Toast.LENGTH_SHORT).show()
        }

        binding.ivClose.setOnClickListener {
            RecyclerUtils.userProgress = 0
            findNavController().navigateUp()
        }
        binding.sbProgress.setOnTouchListener { _, _ -> true }


    }

    private fun generateQuestion() {
        if (counter > 4) {
            findNavController().navigate(R.id.action_lessonQuizScreen_to_completedScreen)
        } else {
            binding.tvTitle.text = data[counter].termin
            description = data[counter].termin
            answer = data[counter].description
            adapter.submitList(data.map { it.description }.shuffled())
            ++counter
            binding.sbProgress.progress = counter + 5
            binding.tvPoints.text = LocaleStorage.getPoints().toString()
            selectedAnswer = ""
        }
    }

    private fun checkAnswer(str: String) {
        if (answer == str) {
            val dialog = BottomSheetDialog(requireContext())
            val view = View.inflate(requireContext(), R.layout.dialog_correct, null)
            val dBind = DialogCorrectBinding.bind(view)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.create()
            dBind.tvContinue.setOnClickListener {
                dialog.dismiss()
                LocaleStorage.setPoints(LocaleStorage.getPoints() + 2)
                generateQuestion()
                ++RecyclerUtils.userProgress;
            }
            dBind.ivShare.setOnClickListener {
                val intent2: Intent = Intent()
                intent2.setAction(Intent.ACTION_SEND)
                intent2.type = "text/plain"
                intent2.putExtra(
                    Intent.EXTRA_TEXT,
                    "$description \n\n $answer \n\nPowered by STEMUp"
                )
                startActivity(Intent.createChooser(intent2, "Share via"))

            }
            dialog.show()
        } else {
            val dialog = BottomSheetDialog(requireContext())
            val view = View.inflate(requireContext(), R.layout.dialog_wrong, null)
            val dBind = DialogWrongBinding.bind(view)
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.create()
            dBind.CorrectAnswerTv.text = answer
            dBind.ivShare.setOnClickListener {
                val intent2: Intent = Intent()
                intent2.setAction(Intent.ACTION_SEND)
                intent2.type = "text/plain"
                intent2.putExtra(
                    Intent.EXTRA_TEXT,
                    "$description \n\n $answer \n\nPowered by STEMUp"
                )
                startActivity(Intent.createChooser(intent2, "Share via"))

            }
            dBind.tvContinue.setOnClickListener {
                dialog.dismiss()
                generateQuestion()
            }
            dialog.show()
        }
    }

}