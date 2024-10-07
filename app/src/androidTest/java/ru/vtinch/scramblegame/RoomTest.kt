package ru.vtinch.scramblegame

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.vtinch.scramblegame.load.data.local.WordCache
import ru.vtinch.scramblegame.load.data.local.WordDao
import ru.vtinch.scramblegame.load.data.local.WordDatabase


@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var dao: WordDao
    private lateinit var db : WordDatabase

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            WordDatabase::class.java,
        ).build()

        dao = db.dao()
    }

    @Test
    fun test() = runBlocking{
        dao.addWords(
            listOf(
                WordCache(1,"first"),
                WordCache(2,"second"),
                WordCache(3,"third")
            )
        )

        var word = dao.getWord(1)

        assertEquals(WordCache(1,"first"),word)
        word= dao.getWord(2)
        assertEquals(WordCache(2,"second"),word)
        word= dao.getWord(3)
        assertEquals(WordCache(3,"third"),word)
    }

    @After
    fun close(){
        db.close()
    }
}