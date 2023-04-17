package uz.behad.elingo.ui.screens

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import uz.behad.elingo.R
import uz.behad.elingo.RecyclerUtils.gone
import uz.behad.elingo.RecyclerUtils.visible
import uz.behad.elingo.data.local.DictEntity
import uz.behad.elingo.databinding.DialogBottomBinding
import uz.behad.elingo.databinding.ScreenDictionaryBinding
import uz.behad.elingo.ui.adapters.DictionaryAdapter
import uz.behad.elingo.ui.presenters.DictionaryViewModel

class DictionaryScreen : Fragment(R.layout.screen_dictionary) {

    private val binding by viewBinding(ScreenDictionaryBinding::bind)
    private val viewModel by viewModels<DictionaryViewModel>()
    private val adapter = DictionaryAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadListLiveData.observe(viewLifecycleOwner, loadListObserver)
        viewModel.searchResultLiveData.observe(viewLifecycleOwner, searchResultObserver)
        viewModel.loadList()
        FastScrollerBuilder(binding.rvDictionary).apply {
            setPadding(0, 100, 0, 100)
            setTrackDrawable(resources.getDrawable(R.drawable.scrooler_bg))
            setThumbDrawable(resources.getDrawable(R.drawable.thumb_bg))
            build()
        }
        binding.rvDictionary.adapter = adapter


        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            if (text!!.isNotEmpty()) {
                viewModel.searchWord(text.toString().trim())
                binding.cancelButton.visibility = View.VISIBLE
            }

            if (text.isEmpty()) {
                binding.cancelButton.visibility = View.INVISIBLE
                viewModel.loadList()
            }
        }


        binding.cancelButton.setOnClickListener {
            binding.etSearch.setText("")
        }

        adapter.initClickListener {
            createDialog(it)
        }


    }

    private fun createDialog(data: DictEntity) {
        val dialog = BottomSheetDialog(requireContext())
        var bottomView = View.inflate(requireContext(), R.layout.dialog_bottom, null)
        dialog.setContentView(bottomView)
        val bottomBinding = DialogBottomBinding.bind(bottomView)
        bottomBinding.apply {
            tvTitle.text = data.term
            tvDescription.text = data.description.replace("&quot;", "\"").replace("&amp;", "'")
                .replace("&apos;", "\"")
        }
        dialog.show()

        bottomBinding.closeBtn.setOnClickListener {
            dialog.dismiss()
        }

        bottomBinding.tvShare.setOnClickListener {
            val intent2: Intent = Intent()
            intent2.setAction(Intent.ACTION_SEND)
            intent2.type = "text/plain"
            intent2.putExtra(
                Intent.EXTRA_TEXT,
                "${data.term}\n\n${
                    data.description.replace("&quot;", "\"").replace("&amp;", "'")
                        .replace("&apos;", "\"")
                }\n\n  \n" +
                        "\n" +
                        "Powered by STEMUp"
            )
            startActivity(Intent.createChooser(intent2, "Share via"))
        }
    }

    private val loadListObserver = Observer<Cursor> {
        adapter.submitCursor(it)
        binding.placeHolder.gone()
    }

    private val searchResultObserver = Observer<Pair<Cursor, String>> {
        adapter.submitCursor(it.first, it.second)
        if (it.first.count == 0) {
            binding.placeHolder.visible()
        } else {
            binding.placeHolder.gone()
        }
    }

}