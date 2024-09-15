package ru.vtinch.scramblegame.core

interface Cache {

    interface Save<T : Any> {
        fun save(value: T)
    }

    interface Restore<T : Any> {
        fun restore(): T
    }

    interface Mutable<T : Any> : Save<T>, Restore<T>

}