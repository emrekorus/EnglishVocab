package com.ybu.englishvocab.ui.quiz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ybu.englishvocab.R
import com.ybu.englishvocab.ui.my_list.AddNewWordActivity

class QuizFragment : Fragment() {

    private val TAG = "QuizFragment"

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_quiz, container, false)
        val makeNounAdverbLayout: LinearLayout = root.findViewById(R.id.makeAdverbQuizLayout)
        val makeVerbQuizLayout: LinearLayout = root.findViewById(R.id.makeVerbQuizLayout)
        val makeAdjectiveQuizLayout: LinearLayout = root.findViewById(R.id.makeAdjectiveQuizLayout)
        val makeMixQuizLayout: LinearLayout = root.findViewById(R.id.makeMixQuizLayout)

        createNotificationChannel();
        Log.d(TAG, "onCreate: Started.");

        makeNounAdverbLayout.setOnClickListener {
            makeQuiz("Adverb")   ;
        }
        makeVerbQuizLayout.setOnClickListener {
            makeQuiz("Verb")   ;
        }
        makeAdjectiveQuizLayout.setOnClickListener {
            makeQuiz("Adjective")   ;
        }
        makeMixQuizLayout.setOnClickListener {
            makeQuiz("Mix")   ;
        }




        return root
    }


    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(requireContext(),CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Example Title")
            .setContentText("Example Description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())){
            notify(notificationId,builder.build())
        }
    }

    private fun makeQuiz(type : String){

        //sendNotification()



        val intent = Intent(activity, MakeQuizActivity::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
    }
}