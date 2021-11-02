package com.example.dindinnassignment

import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Test

class UtilsTest {

    private val DATE_FORMAT ="yyyy-MM-dd'T'HH:mm+ss'Z'"

    @Test
    fun testParseDate() {
        val date = parseDate( DATE_FORMAT, "hh:mm a", "2021-06-10T15:00+00Z")
        assertEquals("03:00 PM",date)
    }

    @Test
    fun testDifferenceInTime() {
        val diff = differenceInTime(DATE_FORMAT, "2021-06-10T15:00+00Z", "2021-06-10T15:02+00Z")
        assertEquals(120000,diff?.toInt())
    }

    @Test
    fun testConvertMillsToTime() {
        val time  = convertMillsToTime(2*60*1000)
        assertEquals("2m",time)
    }
}