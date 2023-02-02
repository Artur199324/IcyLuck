package com.rkgames.basisga

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import com.rkgames.basisga.startIcyLuck.StartIcyLuck
import io.github.a26197993b77e31a4.o7b471d74a5346efb54aa326b892daf01d914ce99.ObfStr
import io.github.a26197993b77e31a4.o7b471d74a5346efb54aa326b892daf01d914ce99.ObfustringThis

val startIcyLuck = StartIcyLuck()
@ObfustringThis
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTaskRoot
            && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            && intent.action != null && intent.action == Intent.ACTION_MAIN
        ) {
            finish()
            return
        }
        setContentView(R.layout.activity_main)
        startIcyLuck.setMainActivity(this)
    }

    fun dialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(ObfStr("comBrkgamesubasisga").v("Whfzedooz!!!"))
        builder.setMessage(ObfStr("comBrkgamesubasisga").v("Jc uikoxnqx uionwkloop!"))
        builder.setPositiveButton(
           ObfStr("comBrkgamesubasisga").v("Nseorbz abt")
        ) { dialog: DialogInterface, _: Int ->
            dialog.cancel()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            Process.killProcess(Process.myPid())
        }
        builder.create()
        builder.show()
    }
}