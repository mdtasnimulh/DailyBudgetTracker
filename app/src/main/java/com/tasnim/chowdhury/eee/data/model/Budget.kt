package com.tasnim.chowdhury.eee.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "budget_table")
@Parcelize
data class Budget(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "budgetId")
    val budgetId: Int,

    @ColumnInfo(name = "budgetTitle")
    val budgetTitle: String? = "",

    @ColumnInfo(name = "budgetCategory")
    val budgetCategory: String? = "",

    @ColumnInfo(name = "budgetDescription")
    val budgetDescription: String? = "",

    @ColumnInfo(name = "budgetStartDate")
    val budgetStartDate: String? = "",

    @ColumnInfo(name = "budgetEndDate")
    val budgetEndDate: String? = "",

    @ColumnInfo(name = "budgetAmount")
    val budgetAmount: Double?,

    @ColumnInfo(name = "budgetIcon")
    val budgetIcon: Int?,

    @ColumnInfo(name = "budgetIconBg")
    val budgetIconBg: Int?,

    @ColumnInfo(name = "budgetTimeStamp")
    val budgetTimeStamp: String? = "",

    ): Parcelable
