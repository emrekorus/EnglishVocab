package com.ybu.englishvocab.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Vocab
import com.ybu.englishvocab.service.DatabaseHelper
import com.ybu.englishvocab.utils.VocabAdapter


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    lateinit var db: DatabaseHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val list: ListView = root.findViewById(R.id.theList)
        val theFilter: EditText = root.findViewById(R.id.searchFilter)
        Log.d(TAG, "onCreate: Started.");

        //Loading all vocabs from database.
        db = DatabaseHelper(requireContext());
        val vocabList = db.getAllVocabs();

        Log.d(TAG, "Tag Count: " + db.getAllVocabs().size);

        val adapter = VocabAdapter(requireContext(), vocabList)
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->

            val intent = Intent(activity, VocabDetailActivity::class.java)
            intent.putExtra("vocab", vocabList[position])
            startActivity(intent)
        }

        theFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                adapter.filter(charSequence.toString());
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        return root
    }
}