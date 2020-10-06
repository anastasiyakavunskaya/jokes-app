package com.example.jokesapp.ui.web

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.jokesapp.R

class WebFragment : Fragment() {

    private lateinit var notificationsViewModel: WebViewModel
    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(WebViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_web, container, false)
        webView = root.findViewById(R.id.view_web)
        if (savedInstanceState != null) webView.restoreState(savedInstanceState)
        else webView.loadUrl("http://www.icndb.com/api/")
        webView.webViewClient = MyWebViewClient()
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        return root

    }
    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    private class MyWebViewClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            view.loadUrl(url)
            return true
        }


    }
}