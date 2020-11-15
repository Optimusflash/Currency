package com.optimus.currency.ui.privatbank.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 * Created by Dmitriy Chebotar on 14.11.2020.
 */
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var onDateSelected: OnDateSetListener? = null
    private val calendar = Calendar.getInstance()

    companion object {
        fun newInstance(listener: OnDateSetListener) = DatePickerFragment().apply {
            onDateSelected = listener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(), this, year, month, day).apply {
            datePicker.maxDate = Calendar.getInstance().timeInMillis
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        onDateSelected?.onDateSet(calendar.timeInMillis)
    }

    interface OnDateSetListener {
        fun onDateSet(dateInMillis: Long)
    }
}