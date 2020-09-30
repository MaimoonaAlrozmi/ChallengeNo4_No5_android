package com.maimoona.challengeno4_no5_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button;
    private lateinit var falseButton: Button;
    private lateinit var nextButton: Button;
    private lateinit var questionTextView: TextView;
    private lateinit var prevImageButton: ImageButton;
    private lateinit var nextImageButton: ImageButton;

    private val questionBank = listOf(

        Question(R.string.q1, true),
        Question(R.string.q2, true),
        Question(R.string.q3, false),
        Question(R.string.q4, false),
        Question(R.string.q5, false),
        Question(R.string.q6, true)
    )

    private var currentIndex = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        // nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        nextImageButton = findViewById(R.id.next_img_button);
        prevImageButton = findViewById(R.id.prev_img_button);

        checkAnsewr(true)


        trueButton.setOnClickListener {

            checkAnsewr(true)
        }

        falseButton.setOnClickListener {

            checkAnsewr(false)
        }

        updateQuestion();

        nextImageButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size;
            updateQuestion();
        }


        prevImageButton.setOnClickListener {


            ///////////////////////********* another way to write code **********///////////////////////////////

              currentIndex = (currentIndex - 1) % questionBank.size;
            if (currentIndex == -1) currentIndex = questionBank.lastIndex

            ///////////////////////********* another way to write code **********///////////////////////////////


            updateQuestion();

            /*  if (currentIndex >0 ) {
                currentIndex = (currentIndex - 1) % questionBank.size
            }else{
                prevImageButton.isEnabled=false

            }*/

            /*
* currentIndex = (currentIndex - 1) % questionBank.size
	if (currentIndex==-1) {
	currentIndex = questionBank.size-1
	}*/

        }

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size;
            updateQuestion();
        }
    }


    fun updateQuestion() {
        val questionText = questionBank[currentIndex].textResId;
        questionTextView.setText(questionText);

    }

    fun checkAnsewr(userAnswer: Boolean) {
        var correctAnswer = questionBank[currentIndex].answer;
        val messageResId = if (userAnswer == correctAnswer) {

            R.string.correct_toast
        } else {

            R.string.incorrect_toast
        }

        var t = Toast.makeText(this, messageResId, Toast.LENGTH_LONG)
        t.setGravity(Gravity.TOP, 0, 250)
        t.show()

    }
}
