package ru.ulizina.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var s = ""
    var isNewOp = true
    var dot = false
    var count_n=0
    var count_m =0
    var m = "1"
    var buClickValue = ""
    var equal = 0
    var buttonEqual = false
    fun buttonNumberEvent(view: View) {

        if (isNewOp) {
            resaultLayout.setText("")
        }
        if (buttonEqual){
            cleanEvent()
            buttonEqual = false
        }
        isNewOp = false
        val buSelect = view as Button


        if (count_n>0)
            s = s.substring(0, s.length - count_n)
        if (count_n ==1 && buClickValue == "0") {
            buClickValue = ""
            count_n --
        }else if (count_n ==1 && buClickValue==".")
        {
            buClickValue = "0."
            count_n++
        }
        when (buSelect.id) {
            b0.id -> buClickValue += "0"
            b1.id -> buClickValue += "1"
            b2.id -> buClickValue += "2"
            b3.id -> buClickValue += "3"
            b4.id -> buClickValue += "4"
            b5.id -> buClickValue += "5"
            b6.id -> buClickValue += "6"
            b7.id -> buClickValue += "7"
            b8.id -> buClickValue += "8"
            b9.id -> buClickValue += "9"
            b_dot.id -> {
                if (dot == false) {
                    buClickValue += "."
                }
                dot = true
            }
        }

        if (count <=1)
            resaultLayout.setText(buClickValue)
        else if (count > 1)
            resaultLayout.setText(finalNumber.toString())


        s+= buClickValue
        m = buClickValue
        textInputLayout.setText(s)
        count_n++

        if (op1 == "/" && buClickValue=="0")
            textInputLayout.setText("Нельзя делить на ноль")
    }

    var op = "*"
    var oldNumber =""
    var count = 0
    var finalNumber:Double?=null
    var op1 =""
    fun buOpEvent(view: View)
    {

        count_n =0
        val buSelect = view as Button
        when (buSelect.id) {
                b_mul.id -> op = "*"
                b_div.id -> op = "/"
                b_min.id -> op = "-"
                b_plus.id -> op = "+"
        }

        isNewOp = true
        dot = false


        s += op
        textInputLayout.setText(s)
        if (count ==0) {
            oldNumber = m
        }else if(count>0) {

            val newNumber = m

            when (op1)
            {
                "*" -> finalNumber = oldNumber.toDouble() * newNumber.toDouble()
                "/" -> finalNumber = oldNumber.toDouble() / newNumber.toDouble()
                "+" -> finalNumber= oldNumber.toDouble() + newNumber.toDouble()
                "-" -> finalNumber =oldNumber.toDouble() - newNumber.toDouble()
            }
            resaultLayout.setText("Sum = " + finalNumber.toString())
            isNewOp = true
            textInputLayout.setText(s)
            oldNumber=finalNumber.toString()
        }
        op1=op
        count ++
        buClickValue = ""

    }

    fun buEqualEvent(view: View)
    {
        count_m++
        equal=1
        val newNumber = m
        when (op)
        {
            "*" -> finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            "/" -> finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            "+" -> finalNumber= oldNumber.toDouble() + newNumber.toDouble()
            "-" -> finalNumber =oldNumber.toDouble() - newNumber.toDouble()
        }
        resaultLayout.setText(finalNumber.toString())
        isNewOp = true
        s+="="
        s+=finalNumber
        textInputLayout.setText(s)
        buttonEqual = true
    }

    fun buDelEvent(view: View)
    {
        buClickValue = s.substring(0, s.length - 1)
        s =s.substring(0, s.length - 1)
        textInputLayout.setText(buClickValue)
        resaultLayout.setText(s)
        count_n--
        dot=false
    }

    fun buttonCleanEvent(view: View) {
        cleanEvent()
    }

    fun cleanEvent ()
    {
        buClickValue = ""
        s = ""
        textInputLayout.setText(s)
        resaultLayout.setText(s)
        finalNumber = 0.0
        count_n = 0
        count =0
        isNewOp = true
        dot = false
    }


}