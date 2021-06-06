package com.ybu.englishvocab.ui.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Vocab
import com.ybu.englishvocab.service.DatabaseHelper


class VocabDetailActivity : AppCompatActivity() {

    val CAMERA_CODE: Int = 7777

    private val TAG = "VocabDetailActivity"
    lateinit var tvName: TextView
    lateinit var tvType: TextView
    lateinit var tvDescription: TextView
    lateinit var tvSentence: TextView
    lateinit var tvSynonyms: TextView
    lateinit var tvAntonyms: TextView
    lateinit var img_vocab: ImageView
    lateinit var img_save: ImageView
    var vocab: Vocab? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocab_detail)

        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        initialize()

        Log.d(TAG, "onCreate: Started.")

        vocab = intent.getSerializableExtra("vocab") as? Vocab
        if (vocab != null) {
            tvName.text = vocab!!.word;
            tvType.text = vocab!!.type;
            tvDescription.text = vocab!!.description;
            tvSentence.text = vocab!!.sentence;
            tvSynonyms.text = vocab!!.synonyms;
            tvAntonyms.text = vocab!!.antonyms;
            val bmp = BitmapFactory.decodeByteArray(vocab!!.image, 0, vocab!!.image!!.size)
            img_vocab.setImageBitmap(bmp)
            /*  Picasso.get().load(vocab.image)
                  .error(R.drawable.ic_home_black_24dp).into(img_vocab);*/
            var db: DatabaseHelper = DatabaseHelper(this);
            val isSaved = db.isVocabSaved(vocab!!.id!!)
            if(isSaved){
                img_save.setImageResource(R.drawable.ic_save)
                img_save.contentDescription = "saved"
            }



        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialize() {
        tvName = findViewById(R.id.tvName)
        tvType = findViewById(R.id.tvType)
        tvDescription = findViewById(R.id.tvDescription)
        tvSentence = findViewById(R.id.tvSentence)
        tvSynonyms = findViewById(R.id.tvSynonyms)
        tvAntonyms = findViewById(R.id.tvAntonyms)
        img_vocab = findViewById(R.id.img_vocab)
        img_save = findViewById(R.id.img_save)
    }

    fun saveVocab(view: View) {
        if(img_save.contentDescription == "saved"){
            img_save.setImageResource(R.drawable.ic_save_empty)
            img_save.contentDescription = "save"

            //Database delete Operation
            val db: DatabaseHelper = DatabaseHelper(this)
            db.deleteWordFromMyList(vocab!!.id!!.toInt())
        }
        else if(img_save.contentDescription == "save"){
            img_save.setImageResource(R.drawable.ic_save)
            img_save.contentDescription = "saved"
            //Database Insert Operation
            val db: DatabaseHelper = DatabaseHelper(this)
            val list_id = db.addMyList(vocab!!.id!!.toInt())
        }


    }

    fun changeImage(view: View) {
        val gallery = Intent(Intent.ACTION_PICK)
        gallery.type = "image/*"
        startActivityForResult(gallery, CAMERA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(applicationContext, "$resultCode", Toast.LENGTH_SHORT)
            .show()

        Log.d(TAG, "onActivityResult: $resultCode.")

        if (resultCode == RESULT_OK && requestCode == CAMERA_CODE) {
            Log.d(TAG, "onActivityResult: $resultCode.")



            vocab!!.image = this.contentResolver.openInputStream(data?.data!!)?.buffered()?.use { it.readBytes() }!!

            findViewById<ImageView>(R.id.img_vocab).setImageURI(data.data)


            val db = DatabaseHelper(this);
            db.updateVocab(vocab!!)


        }
    }


}