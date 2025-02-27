package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        var button=findViewById<Button>(R.id.btnStart)
        var ur_name=findViewById<TextView>(R.id.name)
        button.setOnClickListener{
            if(ur_name.text.toString().isEmpty()){
                Toast.makeText(this,"please enter your name",Toast.LENGTH_SHORT).show()
            }else{
                val intent= Intent(this,QuizQuestionsActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }
}