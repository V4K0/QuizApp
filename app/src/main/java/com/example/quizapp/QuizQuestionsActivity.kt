package com.example.quizapp

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener{
    private var mCurrentPosition:Int=1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition:Int=0
    private var bar:ProgressBar? = null
    private var questionprogress:TextView? = null
    private var mainquestion:TextView? = null
    private var image:ImageView? = null
    private var option1:TextView? = null
    private var option2:TextView? = null
    private var option4:TextView? = null
    private var option3:TextView? = null
    private var button: Button?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
          bar =findViewById(R.id.progressBar)
          questionprogress=findViewById<TextView>(R.id.tv_progress)
          mainquestion=findViewById<TextView>(R.id.tv_question)
          image=findViewById<ImageView>(R.id.iv_image)
          option1=findViewById<TextView>(R.id.tv_option_one)
          option2=findViewById<TextView>(R.id.tv_option_two)
          option3=findViewById<TextView>(R.id.tv_option_three)
          option4=findViewById<TextView>(R.id.tv_option_four)
          button=findViewById(R.id.btn_submit)
        mQuestionsList=constraints.getQuestions()

        defaultOptionsView()
        setQuestion()

        option1!!.setOnClickListener(this)
        option2!!.setOnClickListener(this)
        option3!!.setOnClickListener(this)
        option4!!.setOnClickListener(this)
        button!!.setOnClickListener(this)
    }
private fun setQuestion(){
      answerView(5,R.drawable.default_optilon_border_bg)
    if (mCurrentPosition==mQuestionsList!!.size){
        button!!.text="FINISH"
    }else{
        button!!.text="SUBMIT"
    }

    var question = mQuestionsList!![mCurrentPosition-1]
    bar!!.progress=mCurrentPosition
    questionprogress!!.text="$mCurrentPosition"+"/"+ bar!!.max
    mainquestion!!.text=question.question
    image!!.setImageResource(question.image)
    option1!!.text=question.optionOne
    option2!!.text=question.optionTwo
    option3!!.text=question.optionThree
    option4!!.text=question.optionFour
}
    private fun defaultOptionsView(){
        val options=ArrayList<TextView>()
        option1?.let { options.add(0, it) }
        option2?.let { options.add(1, it) }
        option3?.let { options.add(2, it) }
        option4?.let { options.add(3, it) }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_optilon_border_bg)

        }



    }

    override fun onClick(v: View?) {
           when(v?.id){
               R.id.tv_option_one ->{
                   option1?.let { selectedOptionView(it, 1) }
               }
               R.id.tv_option_two ->{
                   option2?.let { selectedOptionView(it, 2) }
               }
               R.id.tv_option_three ->{
                   option3?.let { selectedOptionView(it, 3) }
               }
               R.id.tv_option_four ->{
                   option4?.let { selectedOptionView(it, 4) }
               }
               R.id.btn_submit ->{
                       if (mSelectedOptionPosition==0){
                           mCurrentPosition++

                   when{
                       mCurrentPosition<=mQuestionsList!!.size->{
                           setQuestion()
                       }else->{
                       Toast.makeText(this,"you have successfully completed the quiz",Toast.LENGTH_SHORT).show()
                   }
                   }
                       }else{
                           val question = mQuestionsList!![mCurrentPosition-1]
                           if (question.correctAnswer!=mSelectedOptionPosition){
                               answerView(mSelectedOptionPosition,R.drawable.incorrect_optilon_border_bg)
                           }
                           answerView(question.correctAnswer,R.drawable.correct_optilon_border_bg)
                        if (mCurrentPosition==mQuestionsList!!.size){
                            button!!.text="FINISH"
                        }else{
                            button!!.text="GO TO NEXT QUESTION"
                        }
                           mSelectedOptionPosition=0
                       }
               }
           }
    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                option1!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                option2!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                option3!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                option4!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            5->{
                option1!!.background=ContextCompat.getDrawable(this,drawableView)
                option2!!.background=ContextCompat.getDrawable(this,drawableView)
                option3!!.background=ContextCompat.getDrawable(this,drawableView)
                option4!!.background=ContextCompat.getDrawable(this,drawableView)
            }
        }

    }
    private fun selectedOptionView(tv:TextView,SelectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition=SelectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_optilon_border_bg)

    }
}