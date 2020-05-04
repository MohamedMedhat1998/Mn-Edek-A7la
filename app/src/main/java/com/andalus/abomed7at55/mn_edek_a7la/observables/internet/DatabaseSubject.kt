package com.andalus.abomed7at55.mn_edek_a7la.observables.internet

import com.andalus.abomed7at55.mn_edek_a7la.observables.Observable
import com.andalus.abomed7at55.mn_edek_a7la.observables.Observer
import com.andalus.abomed7at55.mn_edek_a7la.observables.internet.DatabaseState.Offline

class DatabaseSubject private constructor() : Observable<DatabaseState> {

    private val observers = mutableListOf<Observer<DatabaseState>>()
    private var state: DatabaseState = Offline

    companion object {
        private var databaseSubject: DatabaseSubject? = null

        @JvmStatic
        public fun getInstance(): DatabaseSubject {
            if (databaseSubject == null) {
                databaseSubject = DatabaseSubject()
            }
            return databaseSubject!!
        }
    }

    override fun registerObserver(o: Observer<DatabaseState>) {
        observers.add(o)
    }

    override fun removeObserver(o: Observer<DatabaseState>) {
        if (observers.indexOf(o) >= 0) {
            observers.remove(o)
        }
    }

    override fun notifyObservers(t: DatabaseState) {
        observers.forEach {
            it.update(t)
        }
    }

    fun setState(state: DatabaseState) {
        this.state = state
        notifyObservers(state)
    }
}