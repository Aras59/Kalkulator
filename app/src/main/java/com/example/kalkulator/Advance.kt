package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.*

class Advance : AppCompatActivity() {

    lateinit var textView: TextView
    var negativenumber: Boolean = false;
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false
    var operatorS:String = ""
    var result: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_advance)
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

    fun clearView(view: View){
        lastNumeric = false
        lastDot = false
        if(textView.text==""){
            result=0.0
        }else{
            textView.text = ""
        }
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
                "X^Y"->{
                    operatorS = "X^Y"
                }
            }
        }else{
            Toast. makeText(applicationContext,"Put number!", Toast. LENGTH_SHORT).show()
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
                        Toast. makeText(applicationContext,"You cannot divide by zero!", Toast. LENGTH_SHORT).show()
                        textView.text=""
                     }else{
                        result/=number.toDouble()
                    }
                }
                "X^Y"->{
                    result = result.pow(number.toDouble())
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

    fun functions(view: View){
        if(lastNumeric){
            val function:String = (view as Button).text.toString()
            when(function){
                "SIN" ->{
                    val number:String
                    number = textView.text.toString()
                    textView.text =""
                    textView.append(sin(number.toDouble()).toString())
                }
                "COS"->{
                    val number:String
                    number = textView.text.toString()
                    textView.text =""
                    textView.append(kotlin.math.cos(number.toDouble()).toString())
                }
                "TAN"->{
                    val number:String
                    number = textView.text.toString()
                    textView.text =""
                    textView.append(kotlin.math.tan(number.toDouble()).toString())
                }
                "SQRT"->{
                    val number:String
                    number = textView.text.toString()
                    textView.text =""
                    textView.append(sqrt(number.toDouble()).toString())
                }
                "X^2"->{
                    val number:String
                    number = textView.text.toString()
                    textView.text =""
                    textView.append((number.toDouble()*number.toDouble()).toString())
                }
                "LN"->{
                    val number:String
                    number = textView.text.toString()
                    if(number.toDouble()<=0){
                        Toast. makeText(applicationContext,"Wrong Number!", Toast. LENGTH_SHORT).show()
                        textView.text =""
                        lastNumeric=false
                        lastDot=false
                        result=0.0
                    }else{
                        textView.text =""
                        textView.append(ln(number.toDouble()).toString())
                    }
                }
                "LOG" ->{
                    val number:String
                    number = textView.text.toString()
                    if(number.toDouble()<=0){
                        Toast. makeText(applicationContext,"Wrong Number!", Toast. LENGTH_SHORT).show()
                        textView.text =""
                        lastNumeric=false
                        lastDot=false
                        result=0.0
                    }else{
                        textView.text =""
                        textView.append(log10(number.toDouble()).toString())
                    }
                }
                "%"->{
                    val number:String
                    number = textView.text.toString()
                    textView.text =""
                    textView.append((number.toDouble()/100.0).toString())
                }

            }
        }else{
            Toast. makeText(applicationContext,"Put number!", Toast. LENGTH_SHORT).show()
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