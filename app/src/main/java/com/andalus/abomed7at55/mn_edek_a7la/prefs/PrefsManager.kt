package com.andalus.abomed7at55.mn_edek_a7la.prefs

interface PrefsManager<K, V> {

    fun save(key: K, value: V): Boolean

    fun load(): List<K>

    fun invert(key: K) : V

    fun get(key: K) : V

}