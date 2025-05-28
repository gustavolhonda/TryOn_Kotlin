package com.example.atividadeavaliativa1

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.atividadeavaliativa1.room.AppDatabase
import com.example.atividadeavaliativa1.room.CartItem
import com.example.atividadeavaliativa1.room.CartItemDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var cartItemDao: CartItemDao
    private lateinit var db: AppDatabase

    val CART_ITEM1 = CartItem(
        productId = 1,
        name = "Produto 1",
        price = 99.99,
        shopName = "Loja 1",
        shopCategory = "Categoria 1",
        description = "Descrição 1",
        rating = 4.5,
        size = "M",
        imageUrl = "url1",
        quantity = 1
    )

    val CART_ITEM2 = CartItem(
        productId = 2,
        name = "Produto 2",
        price = 149.99,
        shopName = "Loja 2",
        shopCategory = "Categoria 2",
        description = "Descrição 2",
        rating = 4.0,
        size = "G",
        imageUrl = "url2",
        quantity = 2
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        cartItemDao = db.cartItemDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testEmpty() = runTest {
        cartItemDao.getAllCartItems().test {
            val list = awaitItem()
            Assert.assertEquals(0, list.size)
            cancel()
        }
    }

    @Test
    fun testInsertAndSelect() = runTest {
        cartItemDao.insertCartItem(CART_ITEM1)
        cartItemDao.insertCartItem(CART_ITEM2)
        cartItemDao.getAllCartItems().test {
            val list = awaitItem()
            Assert.assertEquals(2, list.size)
            Assert.assertEquals("Produto 1", list[0].name)
            Assert.assertEquals("Produto 2", list[1].name)
            cancel()
        }
    }

    @Test
    fun testDelete() = runTest {
        cartItemDao.insertCartItem(CART_ITEM1)
        cartItemDao.getAllCartItems().test {
            val list = awaitItem()
            Assert.assertEquals(1, list.size)
            val insertedItem = list[0]
            cartItemDao.deleteCartItem(insertedItem)
            val listAfterDelete = awaitItem()
            Assert.assertEquals(0, listAfterDelete.size)
            cancel()
        }
    }
} 