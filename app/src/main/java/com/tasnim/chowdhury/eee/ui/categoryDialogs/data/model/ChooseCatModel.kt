package com.tasnim.chowdhury.eee.ui.categoryDialogs.data.model

import androidx.room.ColumnInfo

data class ChooseCatModel (
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "background")
    val background: Int?,

    @ColumnInfo(name = "catParent")
    val catParent: String?,

    @ColumnInfo(name = "catIcon")
    val catIcon: Int?,
)