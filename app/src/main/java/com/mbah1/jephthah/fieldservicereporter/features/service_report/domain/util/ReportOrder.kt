package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util

sealed class ReportOrder(val orderType: OrderType){
    class Month(orderType: OrderType): ReportOrder(orderType)
    class Date(orderType: OrderType): ReportOrder(orderType)

    fun copy(orderType: OrderType): ReportOrder {
        return when(this){
            is Month -> Month(orderType)
            is Date -> Date(orderType)
        }
    }

}
