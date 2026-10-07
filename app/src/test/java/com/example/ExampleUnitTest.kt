package com.example

import com.example.data.NewsCatalog
import com.example.data.ToolCatalog
import com.example.model.ToolCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testToolCatalogContains450CategorizedTools() {
        val tools = ToolCatalog.allTools
        assertEquals("Total tools must equal 450", 450, tools.size)

        // Verify each category has exactly 30 tools
        val categoryCounts = tools.groupBy { it.category }
        assertEquals("Must have 15 categories", 15, categoryCounts.size)

        ToolCategory.values().forEach { cat ->
            val count = categoryCounts[cat]?.size ?: 0
            assertEquals("Category ${cat.title} must have 30 tools", 30, count)
        }
    }

    @Test
    fun testNewsCatalogIntegrity() {
        val news = NewsCatalog.sampleNews
        assertTrue("News catalog should contain articles", news.isNotEmpty())
        news.forEach { item ->
            assertNotNull(item.id)
            assertTrue(item.title.isNotEmpty())
            assertTrue(item.summary.isNotEmpty())
            assertTrue(item.content.isNotEmpty())
            assertTrue(item.readMinutes > 0)
        }
    }
}
