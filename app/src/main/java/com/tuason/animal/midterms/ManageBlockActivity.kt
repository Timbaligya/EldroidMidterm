package com.tuason.animal.midterms

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.tuason.animal.midterms.SharedPreferencesManager

class ManageBlockActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<String>
    private val blockedAnimals = mutableListOf<String>()
    private val sharedPrefsManager by lazy { SharedPreferencesManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_block)

        val listView: ListView = findViewById(R.id.blockedAnimalsListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, blockedAnimals)
        listView.adapter = adapter

        val unblockButton: Button = findViewById(R.id.unblockButton)

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedAnimal = blockedAnimals[position]

            unblockAnimal(selectedAnimal)

            blockedAnimals.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        unblockButton.setOnClickListener {
            val selectedPosition = listView.checkedItemPosition
            if (selectedPosition != ListView.INVALID_POSITION) {
                val animalName = blockedAnimals[selectedPosition]
                sharedPrefsManager.unblockAnimal(animalName)
                blockedAnimals.removeAt(selectedPosition)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun unblockAnimal(animalName: String) {
        sharedPrefsManager.unblockAnimal(animalName)
    }
}