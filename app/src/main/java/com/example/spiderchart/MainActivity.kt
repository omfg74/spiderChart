package com.example.spiderchart

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    var mainLayout: ConstraintLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainLayout = findViewById(R.id.mainLayout)
//        val thinCircle = ThinCircle(this)
//        mainLayout?.addView(thinCircle)
        val item1 = SpiderItem("test1", 8)
        val item2 = SpiderItem("test2", 6)
        val item3 = SpiderItem("test3", 9)
        val item4 = SpiderItem("test4", 4)
        val item5 = SpiderItem("test5", 1)
        val item6 = SpiderItem("test6", 7)
        val item7 = SpiderItem("test7", 2)
        val item8 = SpiderItem("test8", 3)
        val spiderList = ArrayList<SpiderItem>()
        spiderList.add(item1)
        spiderList.add(item2)
        spiderList.add(item3)
        spiderList.add(item4)
        spiderList.add(item5)
        spiderList.add(item6)
        spiderList.add(item7)
        spiderList.add(item8)
        val spiderChart = SpiderChart(this)
        spiderChart.setItems(spiderList)
        mainLayout?.addView(spiderChart)
    }


}