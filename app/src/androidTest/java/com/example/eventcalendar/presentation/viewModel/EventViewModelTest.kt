package com.example.eventcalendar.presentation.viewModel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eventcalendar.getOrAwaitValue
import com.example.eventcalendar.model.Event
import com.example.eventcalendar.model.EventDatabase
import com.example.eventcalendar.model.EventRepo
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class EventViewModelTest : TestCase(){

    private lateinit var eventDatabase: EventDatabase
    private lateinit var viewModel: EventViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        eventDatabase = Room.inMemoryDatabaseBuilder(context, EventDatabase::class.java).allowMainThreadQueries().build()
        val dataRepo = EventRepo(eventDatabase.eventDao)
        viewModel = EventViewModel(dataRepo)
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        eventDatabase.close()
    }

    @Test
    fun testAddingEvent(){
        viewModel.saveOrUpdate(Event(0, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(),"TestUnit","Nothing"),true)
        val result = viewModel.events.getOrAwaitValue().find {
            it.title == "TestUnit" && it.description == "Nothing"
        }
        assertTrue(result != null)
    }
}