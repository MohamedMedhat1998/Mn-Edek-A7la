package com.andalus.abomed7at55.mn_edek_a7la.observables

interface Observable<T : State> {
    fun registerObserver(o: Observer<T>)
    fun removeObserver(o: Observer<T>)
    fun notifyObservers(t: T)
}