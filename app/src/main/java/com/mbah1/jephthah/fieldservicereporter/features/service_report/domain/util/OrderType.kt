package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
