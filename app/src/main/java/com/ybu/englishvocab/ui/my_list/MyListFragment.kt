package com.ybu.englishvocab.ui.my_list

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Vocab
import com.ybu.englishvocab.service.DatabaseHelper
import com.ybu.englishvocab.ui.home.VocabDetailActivity
import com.ybu.englishvocab.utils.VocabAdapter

class MyListFragment : Fragment() {

    private val TAG = "MyListFragment"

    lateinit var db: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_my_list, container, false)
        val list: ListView = root.findViewById(R.id.theList)
        val theFilter: EditText = root.findViewById(R.id.searchFilter)
        val btnAddNewWord: Button = root.findViewById(R.id.btnAddNewWord)
        Log.d(TAG, "onCreate: Started.");

        //Loading myVocabList from database.
        db = DatabaseHelper(requireContext());
        val myVocabList_ids = db.getMyList();
        Log.d(TAG, "myVocabList: " + myVocabList_ids.toString());
        val myVocabList = ArrayList<Vocab>();
        for (id in myVocabList_ids) {
            myVocabList.add(db.getVocab(id.toLong()))
        }


        val adapter = VocabAdapter(requireContext(), myVocabList)
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity, VocabDetailActivity::class.java)
            intent.putExtra("vocab", myVocabList[position])
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

        btnAddNewWord.setOnClickListener {
            val intent = Intent(activity, AddNewWordActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}