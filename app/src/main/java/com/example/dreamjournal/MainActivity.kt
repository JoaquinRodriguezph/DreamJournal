package com.example.dreamjournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dreamjournal.ui.theme.DreamJournalTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

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

        // Add sample data (you would load actual data here)
        loadSampleData()
    }

    private fun loadSampleData() {
        // This is just sample data; load real data from storage
        journalEntries.add(JournalEntry(1, "I was flying in a sky full of stars.", "2024-07-27"))
    }

    private fun saveCurrentDream(dreamText: String) {
        if (dreamText.isNotEmpty()) {
            val newEntry = JournalEntry(System.currentTimeMillis(), dreamText, getCurrentDate())
            journalEntries.add(0, newEntry) // Add new entry at the beginning
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

        // LazyColumn to display journal entries
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(journalEntries) { entry ->
                JournalEntryItem(entry)
            }
        }

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

@Composable
fun JournalEntryItem(entry: JournalEntry) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = entry.date, style = MaterialTheme.typography.bodySmall)
        Text(text = entry.text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun JournalScreenPreview() {
    DreamJournalTheme {
        JournalScreen(journalEntries = listOf(
            JournalEntry(1, "I was flying in a sky full of stars.", "2024-07-27"),
            JournalEntry(2, "I was swimming in an ocean of chocolate.", "2024-07-26")
        ), onSaveDream = {})
    }
}
