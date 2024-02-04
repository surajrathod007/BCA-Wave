package com.surajrathod.bcawave.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.surajrathod.bcawave.ui.dashboard.home.ProgramItemData

fun Context.sendReport(recipient: String, subject: String, message: String) {
    val mIntent = Intent(Intent.ACTION_SEND)
    mIntent.data = Uri.parse("mailto:")
    mIntent.type = "text/plain"
    mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
    mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    mIntent.putExtra(Intent.EXTRA_TEXT, message)

    try {
        startActivity(Intent.createChooser(mIntent, "Choose Gmail To Send Report..."))
    } catch (e: Exception) {

    }
}

fun Context.shareProgram(data: ProgramItemData) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_TEXT,
        data.content + "\nHey i found an app where you can get all BCA Practical programs for free!! \n Download Now : "
    )
    startActivity(Intent.createChooser(intent, "Share With Friends"))
}

fun Context.copyTextToClipboard(textToCopy: String) {
    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("code", textToCopy)
    clipboardManager.setPrimaryClip(clipData)
    Toast.makeText(this, "Code copied to clipboard", Toast.LENGTH_LONG).show()
}