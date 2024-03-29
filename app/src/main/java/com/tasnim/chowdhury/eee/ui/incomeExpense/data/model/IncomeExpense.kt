package com.tasnim.chowdhury.eee.ui.incomeExpense.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "income_expense_table")
@Parcelize
data class IncomeExpense(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "iEId")
    val iEId: Int,

    @ColumnInfo(name = "iETitle")
    val iETitle: String? = "",

    @ColumnInfo(name = "iEType")
    val iEType: String? = "",

    @ColumnInfo(name = "iENote")
    val iENote: String? = "",

    @ColumnInfo(name = "iETimeStamp")
    val iETimeStamp: String? = "",

    @ColumnInfo(name = "iEAmount")
    val iEAmount: Double?,

    @ColumnInfo(name = "iECategory")
    val iECategory: String? = "",

    @ColumnInfo(name = "iEDate")
    val iEDate: String? = "",

    @ColumnInfo(name = "paymentMethod")
    val paymentMethod: String? = "",

    @ColumnInfo(name = "categoryParent")
    val categoryParent: String? = "",

    @ColumnInfo(name = "categoryIcon")
    val categoryIcon: Int,

    @ColumnInfo(name = "catIconBg")
    val catIconBg: Int,

): Parcelable