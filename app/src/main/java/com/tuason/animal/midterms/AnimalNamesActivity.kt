package com.tuason.animal.midterms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.tuason.animal.midterms.SharedPreferencesManager

class AnimalNamesActivity : AppCompatActivity() {

    private val animalNames = arrayOf("Lion", "Tiger", "Elephant", "Giraffe", "Zebra", "Kangaroo", "Penguin", "Dolphin")
    private val blockedAnimalsList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private val sharedPrefsManager by lazy { SharedPreferencesManager(this) }

    private val updateListReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            refreshAnimalList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_names)

        val listView: ListView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, blockedAnimalsList)
        listView.adapter = adapter

        val filter = IntentFilter("com.tuason.animal.midterms.UPDATE_ANIMAL_LIST")
        registerReceiver(updateListReceiver, filter)

        refreshAnimalList()
    }

    private fun refreshAnimalList() {
        blockedAnimalsList.clear()
        blockedAnimalsList.addAll(sharedPrefsManager.getBlockedAnimals())
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(updateListReceiver)
    }
}