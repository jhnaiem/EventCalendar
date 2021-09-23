package com.example.eventcalendar.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.example.eventcalendar.R
import com.example.eventcalendar.databinding.AddEventDialogBinding


class AddEventDialog() : AppCompatDialogFragment() {

    private lateinit var binding: AddEventDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout. add_event_dialog, null, false);


        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_event_dialog, null)
        builder.setView(dialogView)
            .setTitle("Add an event")
            .setNegativeButton("Cancel",  DialogInterface.OnClickListener{dialog, which ->  } )
            .setPositiveButton("Add", DialogInterface.OnClickListener { dialog, which ->
                var mTitle = binding.etEventTitle.text.toString()
                var mDesc = binding.etEventDesc.text.toString()
                if(!mTitle.isBlank() && !mDesc.isBlank()){

                }
            })

        builder.apply { setView(binding.root) }

        return builder.create()
    }
}