package ru.vtinch.scramblegame

interface QuestionLiveDataWrapper {

    interface Read: LiveDataWrapper.Read<String>
    interface Update: LiveDataWrapper.Update<String>
    interface Mutable : Read, Update

    class Base: Mutable, LiveDataWrapper.Abstract<String>()

}