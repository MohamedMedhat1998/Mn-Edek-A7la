package com.andalus.abomed7at55.mn_edek_a7la.utils

class CheckList(private val onComplete: () -> Unit) {

    private val checklist = mutableMapOf<String, Boolean>()

    fun register(item: String) {
        checklist[item] = false
    }

    fun check(item: String) {
        checklist[item] = true
        isComplete()
    }

    fun get(item: String): Boolean {
        return checklist[item] ?: false
    }

    private fun isComplete() {
        var completed = true
        checklist.entries.forEach {
            if (!it.value) {
                completed = false
                return@forEach
            }
        }
        if (completed) {
            onComplete.invoke()
        }
    }
}