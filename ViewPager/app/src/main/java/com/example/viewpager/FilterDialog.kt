package com.example.viewpager

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FilterDialog : DialogFragment() {
    private var newTags = mutableListOf<ArticleTag>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val tags = arrayOf(ArticleTag.NEWS, ArticleTag.POLITIC, ArticleTag.TECHNOLOGIES)
        val currentTags = arguments?.getParcelableArray(KEY)
        val checkedItems = mutableListOf<Boolean>()
        tags.forEach {
            if (currentTags!!.contains(it)) {
                checkedItems.add(true)
            } else {
                checkedItems.add(false)
            }
        }
        return AlertDialog.Builder(context)
            .setTitle("Настроить фильтр")
            .setPositiveButton(
                "Применить"
            ) { dialog, which ->
                checkedItems.forEachIndexed { index, b ->
                    if (b) newTags.add(tags[index])
                }
                (parentFragment as? ViewPagerFragment)?.okClicked(newTags)
            }
            .setNegativeButton(
                "Отмена"
            ) { dialog, which ->
                (parentFragment as? ViewPagerFragment)?.cancelClicked()
            }
            .setMultiChoiceItems(
                tags.map { it.text }.toTypedArray(),
                checkedItems.toBooleanArray()
            ) { dialog, which, isChecked ->
                checkedItems.set(which, isChecked)
            }
            .create()
    }

    companion object {
        private const val KEY = "Key"
        fun newInstance(tag: List<ArticleTag>): FilterDialog {
            val dialog = FilterDialog()
            val args = Bundle().apply {
                putParcelableArray(KEY, tag.toTypedArray())
            }
            dialog.arguments = args
            return dialog
        }
    }
}
