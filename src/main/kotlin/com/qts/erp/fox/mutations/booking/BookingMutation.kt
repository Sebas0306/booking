package com.qts.erp.fox.mutations.booking

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.qts.erp.fox.models.booking.BookingCreateInput
import com.qts.erp.fox.models.booking.BookingModel
import com.qts.erp.fox.services.booking.BookingService
import org.springframework.web.bind.annotation.RequestHeader
@DgsComponent
class BookingMutation(private val bookingService: BookingService) {
    @DgsMutation
    fun createBooking(@RequestHeader databaseId: String, @InputArgument data: BookingCreateInput): BookingModel{
        return bookingService.insert(databaseId, data)
    }
}