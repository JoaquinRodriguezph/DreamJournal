package com.example.dreamjournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.viewpager2.widget.ViewPager2
import com.example.dreamjournal.ui.theme.DreamJournalTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var adapter: JournalEntryAdapter
    private val journalEntries = mutableListOf<JournalEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DreamJournalTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    JournalScreen(journalEntries, ::saveCurrentDream)
                }
            }
        }
        // Initialize adapter with empty list
        adapter = JournalEntryAdapter(journalEntries)

        // Add sample data (you would load actual data here)
        loadSampleData()
    }

    private fun loadSampleData() {
        // This is just sample data; load real data from storage
        journalEntries.add(JournalEntry(1, "I was flying in a sky full of stars.", "2024-07-27"))
        adapter.notifyDataSetChanged()
    }

    private fun saveCurrentDream(dreamText: String) {
        if (dreamText.isNotEmpty()) {
            val newEntry = JournalEntry(System.currentTimeMillis(), dreamText, getCurrentDate())
            journalEntries.add(0, newEntry) // Add new entry at the beginning
            adapter.notifyItemInserted(0)
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}

@Composable
fun JournalScreen(journalEntries: List<JournalEntry>, onSaveDream: (String) -> Unit) {
    var dreamText by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // EditText for entering the dream
        OutlinedTextField(
            value = dreamText,
            onValueChange = { dreamText = it },
            label = { Text("Type your dream here...") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ViewPager2 would need to be replaced by a Compose equivalent (Pager or LazyColumn)
        // This is just a placeholder to indicate where it might go
        Text("ViewPager2 goes here")

        Spacer(modifier = Modifier.height(8.dp))

        // Save Button
        Button(
            onClick = {
                onSaveDream(dreamText)
                dreamText = "" // Clear text after saving
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DreamJournalTheme {
        JournalScreen(journalEntries = listOf(), onSaveDream = {})
    }
}
