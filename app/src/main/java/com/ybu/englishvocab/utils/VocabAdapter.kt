package com.ybu.englishvocab.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Vocab
import java.util.*
import kotlin.collections.ArrayList

class VocabAdapter(private val context: Context, private val vocabList: ArrayList<Vocab>) :
    BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var vocabListCopy: ArrayList<Vocab> = ArrayList<Vocab>();
    init {
        this.vocabListCopy.addAll(vocabList);
    }


    override fun getCount(): Int {
        return vocabList.size
    }

    override fun getItem(position: Int): Any {
        return vocabList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cardView = inflater.inflate(R.layout.vocab_card_item, parent, false)
        val tvName = cardView.findViewById<TextView>(R.id.tvName)
        val tvType = cardView.findViewById<TextView>(R.id.tvType)
        val tvDescription = cardView.findViewById<TextView>(R.id.tvDescription)

        tvName.text = vocabList[position].word;
        tvType.text = vocabList[position].type;
        tvDescription.text = vocabList[position].description;

        return cardView
    }

    fun filter(text: String) {
        var filterText = text
        vocabList.clear()
        if (filterText.isEmpty()) {
            vocabList.addAll(vocabListCopy)
        } else {
            filterText = filterText.toLowerCase(Locale.US)
            for (item in vocabListCopy) {
                if (item.word!!.toLowerCase(Locale.US).contains(filterText) || item.type!!.toLowerCase(Locale.US)
                        .contains(filterText)
                ) {
                    vocabList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }


}