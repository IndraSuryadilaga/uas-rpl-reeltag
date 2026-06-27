package com.example.reeltag.ui.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reeltag.data.model.UsabilityResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AnalysisScreen(viewModel: AnalysisViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            "Usability Analysis Results",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                MetricCard(
                    title = "Time on Task",
                    originalValue = uiState.averageOriginalTime?.let { "${formatNumber(it)} sec" },
                    reelTagValue = uiState.averageReelTagTime?.let { "${formatNumber(it)} sec" },
                    improvementLabel = "Time Improvement",
                    improvementValue = uiState.timeImprovementPercent?.let { "${formatNumber(it)}%" }
                )
            }

            item {
                MetricCard(
                    title = "Navigation Click Count",
                    originalValue = uiState.averageOriginalClicks?.let { formatNumber(it) },
                    reelTagValue = uiState.averageReelTagClicks?.let { formatNumber(it) },
                    improvementLabel = "Click Reduction",
                    improvementValue = uiState.clickReductionPercent?.let { "${formatNumber(it)}%" }
                )
            }

            item {
                Text(
                    text = "Session Details",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            if (uiState.results.isEmpty()) {
                item {
                    EmptyStateCard()
                }
            } else {
                items(uiState.results) { result ->
                    SessionCard(result = result)
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    title: String,
    originalValue: String?,
    reelTagValue: String?,
    improvementLabel: String,
    improvementValue: String?
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            MetricRow("Average Original", originalValue ?: "-")
            MetricRow("Average ReelTag", reelTagValue ?: "-")
            MetricRow(improvementLabel, improvementValue ?: "-")
        }
    }
}

@Composable
private fun SessionCard(result: UsabilityResult) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("Session: ${result.sessionId}", color = Color.Cyan)
            Text("Mode: ${result.mode}", color = Color.White)
            Text("Start Time: ${formatTimestamp(result.startTime)}", color = Color.White)
            Text("End Time: ${formatTimestamp(result.endTime)}", color = Color.White)
            Text("Duration: ${result.timeOnTask} seconds", color = Color.White)
            Text("Clicks: ${result.clickCount}", color = Color.White)
            Text(
                "Success: ${if (result.taskSuccess) "Yes" else "No"}",
                color = if (result.taskSuccess) Color.Green else Color(0xFFFF6B6B)
            )
        }
    }
}

@Composable
private fun MetricRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color(0xFFB8B8B8))
        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun EmptyStateCard() {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Belum ada hasil pengujian yang tersimpan.",
                color = Color.White
            )
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return formatter.format(Date(timestamp))
}

private fun formatNumber(value: Double): String {
    return if (value % 1.0 == 0.0) {
        value.toInt().toString()
    } else {
        String.format(Locale.US, "%.2f", value)
    }
}
