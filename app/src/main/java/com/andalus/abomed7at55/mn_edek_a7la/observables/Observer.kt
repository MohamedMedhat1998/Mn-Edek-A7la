package com.andalus.abomed7at55.mn_edek_a7la.observables

interface Observer<T : State> {
    fun update(t: T)
}