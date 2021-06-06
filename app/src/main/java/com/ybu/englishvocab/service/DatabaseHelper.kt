package com.ybu.englishvocab.service

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Question
import com.ybu.englishvocab.models.Vocab
import com.ybu.englishvocab.utils.Functions
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList


class DatabaseHelper(val context: Context?) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {


    companion object {

        private const val TAG: String = "DatabaseHelper"

        //Database Name
        private const val DATABASE_NAME: String = "EnglishVocab"

        //Database Version
        private const val DATABASE_VERSION: Int = 1;

        //Table Names
        private const val TABLE_VOCABS: String = "Vocabs"
        private const val TABLE_MY_VOCAB_LIST: String = "MyVocabList"
        private const val TABLE_QUESTIONS: String = "Questions"

        //Common column names
        private const val KEY_ID: String = "id"

        //VOCABS Table - column names
        private const val KEY_WORD: String = "word"
        private const val KEY_TYPE: String = "type"
        private const val KEY_DESCRIPTION: String = "description"
        private const val KEY_SENTENCE: String = "sentence"
        private const val KEY_SYNONYMS: String = "synonyms"
        private const val KEY_ANTONYMS: String = "antonyms"
        private const val KEY_IMAGE: String = "image"

        //MY_VOCAB_LIST Table - column names
        private const val KEY_VOCAB_ID: String = "vocab_id"

        //QUESTIONS Table - column names
        private const val KEY_QUESTION: String = "question"
        private const val KEY_ANSWER: String = "answer"
        private const val KEY_OPTION_1: String = "option_1"
        private const val KEY_OPTION_2: String = "option_2"
        private const val KEY_OPTION_3: String = "option_3"
        private const val KEY_OPTION_4: String = "option_4"


        // Table Create Statements

        // VOCABS Table create statement
        private const val CREATE_TABLE_VOCABS: String =
            "CREATE TABLE " + TABLE_VOCABS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_WORD + " TEXT," + KEY_TYPE + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_SENTENCE + " TEXT," + KEY_SYNONYMS + " TEXT," + KEY_ANTONYMS + " TEXT," + KEY_IMAGE + " BLOB" + ")";

        // MY_VOCAB_LIST Table create statement
        private const val CREATE_TABLE_MY_VOCAB_LIST: String =
            "CREATE TABLE " + TABLE_MY_VOCAB_LIST + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_VOCAB_ID + " INTEGER" + ")";

        // QUESTIONS Table create statement
        private const val CREATE_TABLE_QUESTIONS: String =
            "CREATE TABLE " + TABLE_QUESTIONS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_QUESTION + " TEXT," + KEY_TYPE + " TEXT," + KEY_ANSWER + " TEXT," + KEY_OPTION_1 + " TEXT," + KEY_OPTION_2 + " TEXT," + KEY_OPTION_3 + " TEXT," + KEY_OPTION_4 + " TEXT" + ")";
    }


    override fun onCreate(db: SQLiteDatabase) {
        // creating required tables
        db.execSQL(CREATE_TABLE_VOCABS)
        db.execSQL(CREATE_TABLE_MY_VOCAB_LIST)
        db.execSQL(CREATE_TABLE_QUESTIONS)

        val vocabList = Functions.loadInitialVocabs(context!!)
        for (vocab in vocabList) {
            val values = ContentValues()
            values.put(KEY_WORD, vocab.word)
            values.put(KEY_TYPE, vocab.type)
            values.put(KEY_DESCRIPTION, vocab.description)
            values.put(KEY_SENTENCE, vocab.sentence)
            values.put(KEY_SYNONYMS, vocab.synonyms)
            values.put(KEY_ANTONYMS, vocab.antonyms)
            values.put(KEY_IMAGE, vocab.image)

            // insert vocab
            val id = db.insert(TABLE_VOCABS, null, values).toInt()
            if (id % 10 == 0) {
                val values2 = ContentValues()
                values2.put(KEY_VOCAB_ID, id)
                db.insert(TABLE_MY_VOCAB_LIST, null, values2)

            }
        }

        val questionList = Functions.loadInitialQuestions(context!!)
        for (question in questionList) {
            val values = ContentValues()
            values.put(KEY_QUESTION, question.question)
            values.put(KEY_TYPE, question.type)
            values.put(KEY_ANSWER, question.answer)
            values.put(KEY_OPTION_1, question.option1)
            values.put(KEY_OPTION_2, question.option2)
            values.put(KEY_OPTION_3, question.option3)
            values.put(KEY_OPTION_4, question.option4)

            // insert question
            db.insert(TABLE_QUESTIONS, null, values)
        }


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOCABS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_VOCAB_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // create new tables
        onCreate(db);
    }

    /*
    * Creating a vocab
    */
    fun createVocab(vocab: Vocab): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_WORD, vocab.word)
        values.put(KEY_TYPE, vocab.type)
        values.put(KEY_DESCRIPTION, vocab.description)
        values.put(KEY_SENTENCE, vocab.sentence)
        values.put(KEY_SYNONYMS, vocab.synonyms)
        values.put(KEY_ANTONYMS, vocab.antonyms)
        values.put(KEY_IMAGE, vocab.image)

        // insert row
        val vocab_id = db.insert(TABLE_VOCABS, null, values)

        return vocab_id
    }

    /*
   * get a vocab
   */
    fun getVocab(vocab_id: Long): Vocab {
        val db = this.readableDatabase

        val selectQuery = "SELECT  * FROM " + TABLE_VOCABS + " WHERE " + KEY_ID + " = " + vocab_id

        Log.e(TAG, selectQuery);

        val c: Cursor? = db.rawQuery(selectQuery, null)

        if (c != null)
            c.moveToFirst();

        val vocab = Vocab()
        vocab.id = c!!.getInt(c.getColumnIndex(KEY_ID))
        vocab.word = c!!.getString(c.getColumnIndex(KEY_WORD))
        vocab.type = c!!.getString(c.getColumnIndex(KEY_TYPE))
        vocab.description = c!!.getString(c.getColumnIndex(KEY_DESCRIPTION))
        vocab.sentence = c!!.getString(c.getColumnIndex(KEY_SENTENCE))
        vocab.synonyms = c!!.getString(c.getColumnIndex(KEY_SYNONYMS))
        vocab.antonyms = c!!.getString(c.getColumnIndex(KEY_ANTONYMS))
        vocab.image = c!!.getBlob(c.getColumnIndex(KEY_IMAGE))

        return vocab
    }

    /*
    * get all vocabs
    */
    fun getAllVocabs(): ArrayList<Vocab> {

        val vocabs: ArrayList<Vocab> = ArrayList<Vocab>()

        val db = this.readableDatabase

        val selectQuery = "SELECT  * FROM " + TABLE_VOCABS

        Log.e(TAG, selectQuery);

        val c: Cursor? = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list

        // looping through all rows and adding to list
        if (c!!.moveToFirst()) {
            do {
                val vocab = Vocab()
                vocab.id = c!!.getInt(c.getColumnIndex(KEY_ID))
                vocab.word = c!!.getString(c.getColumnIndex(KEY_WORD))
                vocab.type = c!!.getString(c.getColumnIndex(KEY_TYPE))
                vocab.description = c!!.getString(c.getColumnIndex(KEY_DESCRIPTION))
                vocab.sentence = c!!.getString(c.getColumnIndex(KEY_SENTENCE))
                vocab.synonyms = c!!.getString(c.getColumnIndex(KEY_SYNONYMS))
                vocab.antonyms = c!!.getString(c.getColumnIndex(KEY_ANTONYMS))
                vocab.image = c!!.getBlob(c.getColumnIndex(KEY_IMAGE))

                // adding to vocab list
                vocabs.add(vocab)
            } while (c.moveToNext())
        }

        Collections.shuffle(vocabs);
        return vocabs
    }


    /*
    * update a vocab
    */
    fun updateVocab(vocab: Vocab): Int {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_WORD, vocab.word)
        values.put(KEY_TYPE, vocab.type)
        values.put(KEY_DESCRIPTION, vocab.description)
        values.put(KEY_SENTENCE, vocab.sentence)
        values.put(KEY_SYNONYMS, vocab.synonyms)
        values.put(KEY_ANTONYMS, vocab.antonyms)
        values.put(KEY_IMAGE, vocab.image)

        // updating row
        return db.update(TABLE_VOCABS, values, KEY_ID + " = ?", arrayOf(vocab.id.toString()))
    }


    /*
    * get all data from my list table
    */
    fun getMyList(): ArrayList<Int> {

        val vocab_ids: ArrayList<Int> = ArrayList<Int>()

        val db = this.readableDatabase

        val selectQuery = "SELECT  * FROM " + TABLE_MY_VOCAB_LIST

        Log.e(TAG, selectQuery);

        val c: Cursor? = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list

        // looping through all rows and adding to list
        if (c!!.moveToFirst()) {
            do {
                val id = c!!.getInt(c.getColumnIndex(KEY_VOCAB_ID))

                // adding to vocab list
                vocab_ids.add(id)
            } while (c.moveToNext())
        }
        return vocab_ids
    }

    /*
 * return boolean for specified vocab saved or not
 */
    fun isVocabSaved(id: Int): Boolean {

        val vocab_ids: ArrayList<Int> = ArrayList<Int>()

        val db = this.readableDatabase

        val selectQuery =  "SELECT  * FROM " + TABLE_MY_VOCAB_LIST + " WHERE " + KEY_VOCAB_ID + " = " + "\"$id\""

        Log.e(TAG, selectQuery);

        val c: Cursor? = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list

        // looping through all rows and adding to list
         val count = c!!.count;
        c.close();
        if(count == 0){
            return false
        }
        return true
    }


    /*
   * add a word to my list table
   */
    fun addMyList(id: Int): Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_VOCAB_ID, id)

        val id = db.insert(TABLE_MY_VOCAB_LIST, null, values)

        return id
    }

    /*
    * remove a word to my list table
    */
    fun deleteWordFromMyList(id: Int): Boolean {

        val db = this.writableDatabase

        return db.delete(TABLE_MY_VOCAB_LIST, KEY_VOCAB_ID + "=" + id, null) > 0;
    }

    /*
  * remove a word to my list table
  */
    fun deleteWordFromMyList2(id: Int, databaseHelper: DatabaseHelper): Boolean {

        return databaseHelper.writableDatabase.delete(TABLE_MY_VOCAB_LIST, KEY_VOCAB_ID + "=" + id, null) > 0;
    }


    /*
    * get all questions with the specified type
    */
    fun getQuestions(type: String): ArrayList<Question> {

        val questions: ArrayList<Question> = ArrayList<Question>()

        val db = this.readableDatabase


        var selectQuery =
            "SELECT  * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_TYPE + " = " + "\"$type\""

        if (type == "Mix") {
            selectQuery =
                "SELECT  * FROM " + TABLE_QUESTIONS
        }
        Log.e(TAG, selectQuery);

        val c: Cursor? = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list

        // looping through all rows and adding to list
        if (c!!.moveToFirst()) {
            do {
                val question = Question()
                question.id = c!!.getInt(c.getColumnIndex(KEY_ID))
                question.question = c!!.getString(c.getColumnIndex(KEY_QUESTION))
                question.type = c!!.getString(c.getColumnIndex(KEY_TYPE))
                question.answer = c!!.getString(c.getColumnIndex(KEY_ANSWER))
                question.option1 = c!!.getString(c.getColumnIndex(KEY_OPTION_1))
                question.option2 = c!!.getString(c.getColumnIndex(KEY_OPTION_2))
                question.option3 = c!!.getString(c.getColumnIndex(KEY_OPTION_3))
                question.option4 = c!!.getString(c.getColumnIndex(KEY_OPTION_4))

                // adding to question list
                questions.add(question)
            } while (c.moveToNext())
        }

        Collections.shuffle(questions);

        if (type == "Mix") {
            val questionList = questions.slice(1..20)
            return questionList as ArrayList<Question>
        }

        return questions
    }


}
