package com.example.dreamjournal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class JournalEntryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_entry, container, false)
        val textViewDate: TextView = view.findViewById(R.id.textViewDate)
        val textViewDream: TextView = view.findViewById(R.id.textViewDream)

        textViewDate.text = arguments?.getString(ARG_DATE)
        textViewDream.text = arguments?.getString(ARG_TEXT)

        return view
    }

    companion object {
        private const val ARG_TEXT = "arg_text"
        private const val ARG_DATE = "arg_date"

        fun newInstance(text: String, date: String) = JournalEntryFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TEXT, text)
                putString(ARG_DATE, date)
            }
        }
    }
}
