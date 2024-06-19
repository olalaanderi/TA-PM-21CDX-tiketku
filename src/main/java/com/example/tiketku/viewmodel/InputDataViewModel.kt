package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class InputDataViewModel(application: Application) : AndroidViewModel(application) {
    var databaseDao: DatabaseDao?

    fun addDataPemesan(
        nama_penumpang: String, keberangkatan: String,
        tujuan: String, tanggal: String, nomor_telepon: String,
        anak_anak: Int, dewasa: Int, harga_tiket: Int, kelas: String, status: String
    ) {
        Completable.fromAction {
            val modelDatabase = ModelDatabase()
            modelDatabase.namaPenumpang = nama_penumpang
            modelDatabase.keberangkatan = keberangkatan
            modelDatabase.tujuan = tujuan
            modelDatabase.tanggal = tanggal
            modelDatabase.nomorTelepon = nomor_telepon
            modelDatabase.anakAnak = anak_anak
            modelDatabase.dewasa = dewasa
            modelDatabase.hargaTiket = harga_tiket
            modelDatabase.kelas = kelas
            modelDatabase.status = status
            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application).appDatabase.databaseDao()
    }

}