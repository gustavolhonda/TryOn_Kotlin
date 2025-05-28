package com.example.atividadeavaliativa1.repository

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class ProductRepositoryTest {

    private val productRepository = ProductRepository(useTestUrl = true)

    @Test
    fun testGetProductsComplete() = runBlocking {
        val products = productRepository.getProducts()
        assertEquals(4, products.size)

        // Produto 1
        val p1 = products[0]
        assertEquals(1, p1.id)
        assertEquals("Camiseta cinza", p1.name)
        assertEquals(100.0, p1.price, 0.001)
        assertEquals("Renner", p1.shopName)
        assertEquals("Casual", p1.shopCategory)
        assertEquals(4.5, p1.rating, 0.001)
        assertEquals(5, p1.avaliableSizes.size)
        assertTrue(p1.avaliableSizes.containsAll(listOf("XS", "S", "M", "L", "XL")))
        assertEquals(3, p1.image.size)
        assertTrue(p1.image[0].startsWith("https://"))

        // Produto 2
        val p2 = products[1]
        assertEquals(2, p2.id)
        assertEquals("Calça baggy", p2.name)
        assertEquals(200.0, p2.price, 0.001)
        assertEquals("Skate Street Wear", p2.shopName)
        assertEquals("Street Wear", p2.shopCategory)
        assertEquals(4.0, p2.rating, 0.001)
        assertEquals(3, p2.avaliableSizes.size)
        assertTrue(p2.avaliableSizes.containsAll(listOf("M", "L", "XL")))
        assertEquals(3, p2.image.size)
        assertTrue(p2.image[1].startsWith("https://"))

        // Produto 3
        val p3 = products[2]
        assertEquals(3, p3.id)
        assertEquals("Kit 2 Moletom Careca", p3.name)
        assertEquals(300.0, p3.price, 0.001)
        assertEquals("Lacoste", p3.shopName)
        assertEquals("Luxo", p3.shopCategory)
        assertEquals(5.0, p3.rating, 0.001)
        assertEquals(5, p3.avaliableSizes.size)
        assertTrue(p3.avaliableSizes.containsAll(listOf("XS", "S", "M", "L", "XL")))
        assertEquals(3, p3.image.size)
        assertTrue(p3.image[2].startsWith("https://"))

        // Produto 4
        val p4 = products[3]
        assertEquals(4, p4.id)
        assertEquals("Boné de Aba Reta", p4.name)
        assertEquals(400.0, p4.price, 0.001)
        assertEquals("Ophicina", p4.shopName)
        assertEquals("Chapéus e Bonés", p4.shopCategory)
        assertEquals(4.0, p4.rating, 0.001)
        assertEquals(3, p4.avaliableSizes.size)
        assertTrue(p4.avaliableSizes.containsAll(listOf("M", "L", "XL")))
        assertEquals(3, p4.image.size)
        assertTrue(p4.image[0].startsWith("https://"))
    }
}
