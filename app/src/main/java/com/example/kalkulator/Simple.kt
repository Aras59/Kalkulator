package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Simple : AppCompatActivity() {

    lateinit var textView: TextView
    var negativenumber: Boolean = false;
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    var operatorS:String = ""
    var result: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_simple)
        textView = findViewById(R.id.textView)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("operator",operatorS)
        outState.putDouble("Result",result)
        outState.putString("textView",textView.text.toString())
        outState.putBoolean("negativenumber",negativenumber)
        outState.putBoolean("lastNumeric",lastNumeric)
        outState.putBoolean("lastDot",lastDot)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState.getString("textView")
        result = savedInstanceState.getDouble("Result")
        operatorS = savedInstanceState.getString("operator").toString()
        negativenumber = savedInstanceState.getBoolean("negativenumber")
        lastNumeric = savedInstanceState.getBoolean("lastNumeric")
        lastDot = savedInstanceState.getBoolean("lastDot")
    }

    fun onDigit(view: View){
        textView.append((view as Button).text)
        lastNumeric = true
        lastDot=false

    }

    fun changeNegative(view: View){
        if(negativenumber){
            negativenumber = false
            var text: String
            text = textView.text.toString()
            text = text.substring(1)
            textView.text=""
            textView.append(text)
        }else{
            negativenumber = true
            var text: String
            text = textView.text.toString()
            text = "-"+text
            textView.text=""
            textView.append(text)
        }
    }

    fun clearAll(view: View){
        lastNumeric = false
        lastDot = false
        textView.text = ""
        result=0.0
    }

    fun clear(view: View){

        var text: String
        text = textView.text.toString()
        if(text.length==1){
            lastNumeric = false
            lastDot = false
        }
        if(text.endsWith(".")){
            lastDot = false
        }
        text = text.dropLast(1)
        textView.text=text
    }

    fun clearView(view: View){
        lastNumeric = false
        lastDot = false
        if(textView.text==""){
            result=0.0
        }else{
            textView.text = ""
        }
    }


    fun onOperator(view: View){
        if(lastNumeric){
            lastNumeric = false
            lastDot = false
            val operator:String = (view as Button).text.toString()
            var number:Double
            number= textView.text.toString().toDouble()
            result=0.0
            result+= number
            textView.text =""
            when(operator){
                "+" ->{
                    operatorS = "+"
                }
                "-"->{
                    operatorS = "-"
                }
                "*"->{
                    operatorS = "*"
                }
                "/"->{
                    operatorS = "/"
                }
            }
        }else{
            Toast. makeText(applicationContext,"Put number!",Toast. LENGTH_SHORT).show()
        }

    }

    fun onEquals(view: View){
        if(lastNumeric && operatorS!=""){

            val number:String
            number = textView.text.toString()
            textView.text =""
            when(operatorS){
                "+"->{
                    result+=number.toDouble()
                }
                "-"->{
                    result-=number.toDouble()
                }
                "*"->{
                    result*=number.toDouble()
                }
                "/"->{
                    if(number.toDouble()==0.0){
                        Toast. makeText(applicationContext,"You cannot divide by zero!",Toast. LENGTH_SHORT).show()
                        textView.text=""
                    }else{
                        result/=number.toDouble()
                    }

                }
            }
            if(result%1.0==0.0){
                var resulti = result.toInt()
                textView.append(resulti.toString())
            }else{
                textView.append(result.toString())
            }

        }
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && lastDot == false){
            textView.append(".")
            //lastNumeric = false
            lastDot = true
        }
    }

}