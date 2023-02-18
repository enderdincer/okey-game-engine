package com.enderdincer.okey.game.engine.commons

fun Boolean.ifTrue(runnable: () -> Unit) {
    if (this) runnable()
}