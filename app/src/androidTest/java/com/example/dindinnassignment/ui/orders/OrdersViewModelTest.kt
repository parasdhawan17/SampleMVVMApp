package com.example.dindinnassignment.ui.orders

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.InstrumentationRegistry
import com.example.dindinnassignment.datamodels.OrderInfo
import com.example.dindinnassignment.differenceInTime
import com.example.dindinnassignment.getOrAwaitValue
import com.example.dindinnassignment.ui.fakedata.FakeOrdersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class OrdersViewModelTest {

    private val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm+ss'Z'"

    private lateinit var viewModel: OrdersViewModel

    private lateinit var fakeOrder : OrderInfo

    private lateinit var firstFakeOrder: OrderInfo.Data

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeDataRepository :FakeOrdersRepository

    @Before
    fun setUp() {

        fakeOrder = getFakeData()

        firstFakeOrder = fakeOrder.data[0]

        fakeDataRepository = FakeOrdersRepository(fakeOrder)

        viewModel = OrdersViewModel(
            fakeDataRepository,
            InstrumentationRegistry.getTargetContext().applicationContext as Application
        )

        // Wait to fetch orders when viewmodel is initialized
        Thread.sleep(1000)
    }

    @Test
    fun ordersFetchedSuccessFullTest() {
        val value = viewModel.ordersLiveData.getOrAwaitValue()
        assert(value.status.success)
    }

    @Test
    fun ordersCountTest() {
        val value = viewModel.ordersLiveData.getOrAwaitValue().data.size
        MatcherAssert.assertThat(value, CoreMatchers.`is`(fakeOrder.data.size))
    }

    @Test
    fun timerTest() {
        val timerCount = viewModel.countDownTimersMap.entries.size
        MatcherAssert.assertThat(timerCount, CoreMatchers.`is`(fakeOrder.data.size))
    }

    @Test
    fun alertTimerTest() {
        val diff =
            differenceInTime(DATE_FORMAT, firstFakeOrder.created_at, firstFakeOrder.alerted_at)
        Thread.sleep(diff!!)
        val timerCount = viewModel.mediaPlayerHashMap.entries.size
        MatcherAssert.assertThat(timerCount, CoreMatchers.`is`(1))
    }

    @Test
    fun accept_OrderDeleteTest() {
        val dataSize = fakeOrder.data.size
        viewModel.accept(firstFakeOrder)
        val ordersCount = viewModel.ordersLiveData.getOrAwaitValue().data.size
        MatcherAssert.assertThat(ordersCount, CoreMatchers.`is`(dataSize - 1))
    }

    @Test
    fun accept_RingToneStopTest() {
        val diff = differenceInTime(
            DATE_FORMAT, firstFakeOrder.created_at,
            firstFakeOrder.alerted_at
        )
        Thread.sleep(diff!!)
        viewModel.accept(firstFakeOrder)
        val isRingToneActive =
            viewModel.mediaPlayerHashMap[firstFakeOrder.id]?.isPlaying ?: false
        MatcherAssert.assertThat(isRingToneActive, CoreMatchers.`is`(false))
    }

    @Test
    fun okayOrderDeleteTest() {
        val dataSize = fakeOrder.data.size
        viewModel.okay(fakeOrder.data[0])
        val ordersCount = viewModel.ordersLiveData.getOrAwaitValue().data.size
        MatcherAssert.assertThat(ordersCount, CoreMatchers.`is`(dataSize - 1))
    }

    @Test
    fun fireRingToneTest() {
        viewModel.fireRingtone(firstFakeOrder)
        val isPlaying = viewModel.mediaPlayerHashMap[firstFakeOrder.id]?.isPlaying?:false
        MatcherAssert.assertThat(isPlaying, CoreMatchers.`is`(true))
    }

    @Test
    fun stopRingToneTest() {
        viewModel.stopRingTone(firstFakeOrder)
        val isPlaying = viewModel.mediaPlayerHashMap[firstFakeOrder.id]?.isPlaying?:false
        MatcherAssert.assertThat(isPlaying, CoreMatchers.`is`(false))
    }

    private fun getFakeData(): OrderInfo {
        val addOn = OrderInfo.Data.Addon(
            id = 21,
            quantity = 3,
            title = "Fried Egg"
        )
        val data1 = OrderInfo.Data(
            addon = arrayListOf(addOn),
            created_at = "2021-06-10T15:00+00Z",
            alerted_at = "2021-06-10T15:01+00Z",
            expired_at = "2021-06-10T15:02+00Z",
            id = 10,
            quantity = 1,
            title = "Special extra large fried rice"
        )

        val addOn2 = OrderInfo.Data.Addon(
            id = 26,
            quantity = 2,
            title = "Extra chicken"
        )
        val addOn3 = OrderInfo.Data.Addon(
            id = 27,
            quantity = 1,
            title = "Sambal"
        )
        val data2 = OrderInfo.Data(
            addon = arrayListOf(addOn2, addOn3),
            created_at = "2021-06-10T15:10+00Z",
            alerted_at = "2021-06-10T15:12+00Z",
            expired_at = "2021-06-10T15:14+00Z",
            id = 11,
            quantity = 2,
            title = "Chicken Noodle"
        )

        val status = OrderInfo.Status(message = "Success", statusCode = 200, success = true)

        return OrderInfo(data = arrayListOf(data1, data2), status = status)
    }
}