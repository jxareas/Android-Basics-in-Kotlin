package com.example.busschedule.database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val dao : ScheduleDao) : ViewModel() {

    fun fullSchedule() : Flow<List<Schedule>> = dao.getAll()

    fun scheduleForStopName(name : String) :  Flow<List<Schedule>> = dao.getByStopName(name)

}

class BusScheduleViewModelFactory(private val dao : ScheduleDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BusScheduleViewModel::class.java))
            return BusScheduleViewModel(dao) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}