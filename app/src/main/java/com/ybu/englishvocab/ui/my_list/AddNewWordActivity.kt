package com.ybu.englishvocab.ui.my_list

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Vocab
import com.ybu.englishvocab.service.DatabaseHelper
import java.io.ByteArrayOutputStream


class AddNewWordActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val TAG = "AddNewWordActivity"

    val CAMERA_CODE: Int = 7777
    lateinit var type: String

    lateinit var etWord: EditText
    lateinit var spinnerType: Spinner
    lateinit var etDescription: EditText
    lateinit var etSentence: EditText
    lateinit var etSynonyms: EditText
    lateinit var etAntonyms: EditText
    lateinit var btnAdd: Button
    lateinit var image: ByteArray
    lateinit var awesomeValidation: AwesomeValidation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_word)

        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        initialize()
        Log.d(TAG, "onCreate: Started.")

        val types = arrayOf("Noun", "Verb", "Adverb", "Adjective", "Phrase or Idiom")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, types);
        spinnerType.adapter = adapter
        spinnerType.onItemSelectedListener = this;


        //Defining Validation Styles for each form item
        defineValidation()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 66666)
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

        //Initialization UI Fields
        etWord = findViewById(R.id.etWord)
        spinnerType = findViewById(R.id.spinnerType)
        etDescription = findViewById(R.id.etDescription)
        etSentence = findViewById(R.id.etSentence)
        etSynonyms = findViewById(R.id.etSynonyms)
        etAntonyms = findViewById(R.id.etAntonyms)
        btnAdd = findViewById(R.id.btnAdd)

    }

    private fun defineValidation() {

        //Initialization Validation Style
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)

        //Add Validation For Word
        awesomeValidation.addValidation(
            this,
            R.id.etWord,
            RegexTemplate.NOT_EMPTY,
            R.string.invalid_word
        )

        //Add Validation For Description
        awesomeValidation.addValidation(
            this,
            R.id.etDescription,
            RegexTemplate.NOT_EMPTY,
            R.string.invalid_description
        )

        //Add Validation For Description
        awesomeValidation.addValidation(
            this,
            R.id.etSentence,
            RegexTemplate.NOT_EMPTY,
            R.string.invalid_sentence
        )

        //Add Validation For Description
        awesomeValidation.addValidation(
            this,
            R.id.etSynonyms,
            RegexTemplate.NOT_EMPTY,
            R.string.invalid_synonyms
        )

        //Add Validation For Description
        awesomeValidation.addValidation(
            this,
            R.id.etAntonyms,
            RegexTemplate.NOT_EMPTY,
            R.string.invalid_antonyms
        )

    }

    fun addNewWord(view: View) {
        //Check Validation
        if (awesomeValidation.validate()) {
            //On Success
            Toast.makeText(applicationContext, "Form Validate Successfully...", Toast.LENGTH_SHORT)
                .show()

            //Database Insert Operation
            val db: DatabaseHelper = DatabaseHelper(this)

            val vocab: Vocab = Vocab(
                etWord.text.toString(),
                type,
                etDescription.text.toString(),
                etSentence.text.toString(),
                etSynonyms.text.toString(),
                etAntonyms.text.toString(),
                image
            )


            val vocab_id = db.createVocab(vocab)
            val list_id = db.addMyList(vocab_id.toInt())
            Log.e(
                TAG,
                "Tag Count: " + vocab_id + " " + db.getAllVocabs().size + " " + db.getAllVocabs()
                    .toString() + "\n"
            );

        } else {
            //On Fail
            Toast.makeText(applicationContext, "Form Validation Failed...", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun camera(view: View) {
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_CODE)
        /*
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CAMERA_CODE)
        */
         */

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
/*
        if (requestCode === CAMERA_CODE) {
            if (resultCode === Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            this.getContentResolver(),
                            data.data
                        )
                        val stream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
                        image = stream.toByteArray()

                        findViewById<ImageView>(R.id.img_vocab).setImageBitmap(bitmap)
                        findViewById<LinearLayout>(R.id.layoutImageChoose).visibility = View.GONE
                        findViewById<ImageView>(R.id.img_vocab).visibility = View.VISIBLE
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode === Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }*/


        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {
            val bitmap: Bitmap? = data!!.extras!!.get("data") as Bitmap

            val stream = ByteArrayOutputStream()
            bitmap!!.compress(Bitmap.CompressFormat.PNG, 25, stream)
            image = stream.toByteArray()

            findViewById<ImageView>(R.id.img_vocab).setImageBitmap(bitmap)
            findViewById<LinearLayout>(R.id.layoutImageChoose).visibility = View.GONE
            findViewById<ImageView>(R.id.img_vocab).visibility = View.VISIBLE
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(TAG, "onNothingSelected: Started.")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected: " + parent?.getItemAtPosition(position))
        type = parent?.getItemAtPosition(position).toString()
    }

}