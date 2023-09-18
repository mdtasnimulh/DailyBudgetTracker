package com.tasnim.chowdhury.eee.ui.incomeExpense

import androidx.room.ColumnInfo

data class ChooseCatModel (
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "color")
    val color: String?,

    @ColumnInfo(name = "catParent")
    val catParent: String?,

    @ColumnInfo(name = "catIcon")
    val catIcon: Int?,
)