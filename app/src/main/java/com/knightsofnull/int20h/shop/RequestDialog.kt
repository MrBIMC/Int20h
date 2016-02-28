package com.knightsofnull.int20h.shop

import android.app.Dialog
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.knightsofnull.int20h.R
import com.knightsofnull.int20h.model.Category
import com.knightsofnull.int20h.model.Request

/**
 * Created by yarolegovich on 28.02.2016.
 */
class RequestDialog(context: Context) {

    private val dialog: Dialog
    private val view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_request, null)
        dialog = AlertDialog.Builder(context)
                .setView(view)
                .create()
    }

    fun onAcceptButtonClicked(callback: (Request) -> Unit): RequestDialog {
        view.findViewById(R.id.dialog_btn_yes).setOnClickListener { v ->
            val name = view.findViewById(R.id.title) as EditText
            val description = view.findViewById(R.id.description) as EditText

            if (!name.text.isEmpty() && !description.text.isEmpty()) {
                callback(Request(Category.ALL.catId,
                        name.text.toString(),
                        description.text.toString(), 1)
                )
                dialog.dismiss()
            } else {
                Toast.makeText(view.context,
                        "Заполните все поля пожалуйста",
                        Toast.LENGTH_SHORT)
                        .show()
            }
        }
        return this
    }

    fun show(): Dialog {
        dialog.show()
        return dialog
    }
}