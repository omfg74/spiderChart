package com.example.spiderchart

import android.content.res.Resources

fun Int.dp() = (Resources.getSystem().displayMetrics.density * this).toInt()
fun Int.fdp() = (Resources.getSystem().displayMetrics.density * this)
fun Float.fdp() = (Resources.getSystem().displayMetrics.density * this)