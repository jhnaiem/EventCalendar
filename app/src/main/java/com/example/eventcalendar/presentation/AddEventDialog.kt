package com.example.eventcalendar.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import com.example.eventcalendar.R
import com.example.eventcalendar.databinding.AddEventDialogBinding
import com.example.eventcalendar.model.Event
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.time.LocalDateTime


class AddEventDialog(
    private val selectedDate: LocalDate,
    private val addButtonTracker: Boolean,
    val selectedEvent: Event
) : AppCompatDialogFragment() {

    var listener = WeakReference<DialogButtonListener>(null)
    private lateinit var binding: AddEventDialogBinding
    private lateinit var positiveButtonText: String;
    private lateinit var negativeButtonText: String;
    private lateinit var titleText: String;


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.add_event_dialog,
            null,
            false
        )
        positiveButtonText = if (addButtonTracker) "Add" else "Update"
        negativeButtonText = if (addButtonTracker) "Cancel" else "Delete"
        titleText = if (addButtonTracker) "Add an event" else "Update/Delete and event"


        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)


        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_event_dialog, null)
        builder.setView(dialogView)
        if (!addButtonTracker) {

            binding.etEventTitle.text = selectedEvent.title.toEditable()
            binding.etEventDesc.text = selectedEvent.description.toEditable()

        }
        dialogView.findViewById<EditText>(R.id.etEventTitle)
            .setText(selectedEvent.title.toEditable())
        builder.setView(dialogView)
            .setTitle(titleText)
            .setPositiveButton(
                positiveButtonText,
                DialogInterface.OnClickListener { dialog, which ->

                    var mTitle = binding.etEventTitle.text.toString()
                    var mDesc = binding.etEventDesc.text.toString()

                    if (mTitle.isNotBlank() && mDesc.isNotBlank()) {

                        if (addButtonTracker) {
                            listener.get()?.onSaveButtonClick(
                                Event(
                                    0, selectedDate, LocalDateTime.now(),
                                    LocalDateTime.now(), mTitle, mDesc
                                ), addButtonTracker
                            )
                        } else {
                            listener.get()?.onSaveButtonClick(
                                Event(
                                    selectedEvent.id, selectedDate, selectedEvent.createdDateTime,
                                    LocalDateTime.now(), mTitle, mDesc
                                ), addButtonTracker
                            )
                        }

                    }

                })
            .setNegativeButton(
                negativeButtonText,
                DialogInterface.OnClickListener { dialog, which ->
                    listener.get()?.onCancelButtonClick(selectedEvent,addButtonTracker)
                })

        builder.apply { setView(binding.root) }

        return builder.create()
    }


    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    fun observeClick(listener: DialogButtonListener) {
        this.listener = WeakReference(listener)
    }
}