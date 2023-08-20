package com.simple.simplecalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var screenView: TextView
    lateinit var minus: Button
    lateinit var plus: Button
    lateinit var multi: Button
    lateinit var divide: Button
    lateinit var prcent: Button
    lateinit var previousText: String
    lateinit var resultView: TextView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callViwes()
        findViewById<Button>(R.id.oneBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}1"
        }
        findViewById<Button>(R.id.twoBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}2"
        }
        findViewById<Button>(R.id.threeBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}3"
        }
        findViewById<Button>(R.id.fourBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}4"
        }
        findViewById<Button>(R.id.fiveBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}5"
        }
        findViewById<Button>(R.id.sixBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}6"
        }
        findViewById<Button>(R.id.sevenBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}7"
        }
        findViewById<Button>(R.id.eightBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}8"
        }
        findViewById<Button>(R.id.nineBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}9"
        }
        findViewById<Button>(R.id.zeroBtn).setOnClickListener {
            previousText = screenView.text.toString()
            screenView.text = "${previousText}0"
            Toast.makeText(this, "${screenView.text}", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.dotBut).setOnClickListener {
            previousText = screenView.text.toString()
            if (previousText.contains(".")) {
                Toast.makeText(this, "Stop being STUPID", Toast.LENGTH_SHORT).show()

            } else {
                screenView.text = "${previousText}."
            }
        }
        findViewById<Button>(R.id.cleanBtn).setOnClickListener {
            screenView.text = ""
            resultView.setText("")
        }
        findViewById<Button>(R.id.backBtn).setOnClickListener {

            previousText = screenView.text.toString()
            if (previousText.isNotEmpty()) {
                previousText = previousText.dropLast(1)
                screenView.text = previousText
            }
        }
        findViewById<Button>(R.id.plusBtn).setOnClickListener {
            screenView.append("+")
        }
        findViewById<Button>(R.id.minusBtn).setOnClickListener {
            screenView.append("-")

        }
        findViewById<Button>(R.id.multipleBtn).setOnClickListener {
            screenView.append("x")

        }
        findViewById<Button>(R.id.divideBtn).setOnClickListener {
            screenView.append("÷")


        }
        findViewById<Button>(R.id.equalBtn).setOnClickListener {
            val finalResult = calcOperationd()
            screenView.append(finalResult)
            resultView.setText(finalResult)


        }
    }

    fun callViwes() {
        screenView = findViewById(R.id.operationShow)
        plus = findViewById(R.id.plusBtn)
        divide = findViewById(R.id.divideBtn)
        multi = findViewById(R.id.multipleBtn)
        resultView = findViewById(R.id.resultText)

    }

    fun digitOperator(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (i in screenView.text) {
            if (i.isDigit() || i == '.') {
                currentDigit += i

            } else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(i)
            }

        }
        if (currentDigit != "") {
            list.add(currentDigit.toFloat())

        }
        return list
    }

    fun calculateDivideTimes(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('x') || list.contains('/')) {
            list = multipleAndDivide(list)
        }
        return list

    }

    private fun multipleAndDivide(passedList: MutableList<Any>): MutableList<Any> {
        var newList = mutableListOf<Any>()
        var len = passedList.size
        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < len)
            {
                val operator = passedList[i]
                val nextNum = passedList[i + 1] as Float
                val prevNum = passedList[i - 1] as Float

                when (operator) {

                    'x' -> {
                        newList.add(nextNum *prevNum)
                        len = i + 1

                    }
                    '÷' -> {
                        newList.add(prevNum / nextNum)
                        len = i + 1

                    }
                    else -> {
                        newList.add(prevNum)
                        newList.add(operator)
                    }
                }
            }
            if (i > len) newList.add(passedList[i])
        }
        Log.i("digit",newList.toString())

        return newList

    }

    fun addAndSubtructionOperation(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float
        var len = passedList.size
        for (i in passedList.indices) {
            if (passedList[i] is Char && i < len) {
                val operator = passedList[i]
                val nextNum = passedList[i + 1] as Float
                when (operator) {
                    '+' -> result += nextNum
                    '-' -> result -= nextNum
                }
            }
        }

        return result
    }

    fun calcOperationd(): String {
        val digits = digitOperator()
        if (digits.isEmpty()) return ""

        val timesDivide = calculateDivideTimes(digits)
        if (timesDivide.isEmpty()) return ""
        val resulOperation = addAndSubtructionOperation(timesDivide)
        return resulOperation.toString()
    }
}

