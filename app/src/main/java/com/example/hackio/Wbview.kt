package com.example.hackio

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_wbview.*
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi


class Wbview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wbview)
        val Website= intent.getStringExtra("website")
        if (Website != null) {
            @RequiresApi(Build.VERSION_CODES.O)
            Contestweb.webViewClient = WebViewClient()
            Contestweb.settings.domStorageEnabled = true;
            val webSettings = Contestweb?.settings
            webSettings?.setJavaScriptEnabled(true)
            Contestweb.loadUrl(Website)
        }
        else
        {
            Toast.makeText(this,"Looks like website have problems ",Toast.LENGTH_SHORT).show()
        }
    }
}