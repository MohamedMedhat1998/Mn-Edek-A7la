package com.andalus.abomed7at55.mn_edek_a7la.observables.internet

import com.andalus.abomed7at55.mn_edek_a7la.observables.State

sealed class DatabaseState : State {
    object Offline : DatabaseState()
    object Updating : DatabaseState()
    object UpToDate : DatabaseState()
}