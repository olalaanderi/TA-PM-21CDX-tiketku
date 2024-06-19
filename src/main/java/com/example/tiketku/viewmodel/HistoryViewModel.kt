package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.database.DatabaseDao
import com.example.model.ModelDatabase


class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    var dataList: LiveData<List<ModelDatabase>>
    var databaseDao: DatabaseDao?

    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            databaseDao?.deleteDataById(uid) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application).appDatabase.databaseDao()
        dataList = databaseDao.getAllData()
    }

}