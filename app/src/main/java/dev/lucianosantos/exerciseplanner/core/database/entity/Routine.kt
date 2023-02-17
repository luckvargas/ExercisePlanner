package dev.lucianosantos.exerciseplanner.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Routine(
    @PrimaryKey val id: String,
    val name: String,
    val daysOfWeek: List<Int>
)