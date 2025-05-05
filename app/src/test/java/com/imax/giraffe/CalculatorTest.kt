package com.imax.giraffe

import com.imax.giraffe.presentation.test.Calculator
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculatorTest {
    @Test
    fun testAdd() {
        val calculator = Calculator()
        val result = calculator.add(2, 3)
        assertEquals(5, result)
    }
}