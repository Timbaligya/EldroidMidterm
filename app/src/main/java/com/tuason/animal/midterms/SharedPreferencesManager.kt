package com.tuason.animal.midterms

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    fun blockAnimal(animalName: String) {
        val blockedAnimals = getBlockedAnimals().toMutableSet()
        blockedAnimals.add(animalName)
        sharedPreferences.edit().putStringSet("blocked_animals", blockedAnimals).apply()
    }

    fun unblockAnimal(animalName: String) {
        val blockedAnimals = getBlockedAnimals().toMutableSet()
        blockedAnimals.remove(animalName)
        sharedPreferences.edit().putStringSet("blocked_animals", blockedAnimals).apply()
    }

    fun getBlockedAnimals(): Set<String> {
        return sharedPreferences.getStringSet("blocked_animals", setOf()) ?: setOf()
    }

    fun getSelectedAnimalName(): String? {
        return sharedPreferences.getString("selected_animal_name", null)
    }

    fun setSelectedAnimalName(animalName: String) {
        sharedPreferences.edit().putString("selected_animal_name", animalName).apply()
    }

    fun clearBlockedAnimals() {
        sharedPreferences.edit().remove("blocked_animals").apply()
    }
}