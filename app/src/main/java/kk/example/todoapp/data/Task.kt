package kk.example.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "complitable") var complitable: Boolean,
)