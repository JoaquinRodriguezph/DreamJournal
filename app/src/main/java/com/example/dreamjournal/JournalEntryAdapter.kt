package com.example.dreamjournal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JournalEntryAdapter(private val journalEntries: List<JournalEntry>) :
    RecyclerView.Adapter<JournalEntryAdapter.JournalEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalEntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_journal_entry, parent, false)
        return JournalEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: JournalEntryViewHolder, position: Int) {
        val entry = journalEntries[position]
        holder.textViewDate.text = entry.date
        holder.textViewDream.text = entry.text
    }

    override fun getItemCount(): Int = journalEntries.size

    inner class JournalEntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewDate: TextView = view.findViewById(R.id.textViewDate)
        val textViewDream: TextView = view.findViewById(R.id.textViewDream)
    }
}
