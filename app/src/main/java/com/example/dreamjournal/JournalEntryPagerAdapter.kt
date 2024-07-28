package com.example.dreamjournal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class JournalEntryPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val journalEntries: List<JournalEntry>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = journalEntries.size

    override fun createFragment(position: Int): Fragment {
        val entry = journalEntries[position]
        return JournalEntryFragment.newInstance(entry.text, entry.date)
    }
}
