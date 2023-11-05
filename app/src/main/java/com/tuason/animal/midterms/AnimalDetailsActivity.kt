package com.tuason.animal.midterms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tuason.animal.midterms.SharedPreferencesManager

class AnimalDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)

        val animalName = intent.getStringExtra("animalName")

        if (animalName != null) {
            val animalDescription = "Description of $animalName"

            val nameTextView: TextView = findViewById(R.id.nameTextView)
            val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
            val blockButton: Button = findViewById(R.id.blockButton)

            nameTextView.text = animalName
            descriptionTextView.text = animalDescription

            blockButton.setOnClickListener {
                val sharedPrefsManager = SharedPreferencesManager(this)
                sharedPrefsManager.setSelectedAnimalName(animalName)
                sharedPrefsManager.blockAnimal(animalName)
                finish()

                val updateListIntent = Intent("com.yourapp.UPDATE_ANIMAL_LIST")
                sendBroadcast(updateListIntent)
            }
        } else {
            Toast.makeText(this, "Animal name is missing.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}