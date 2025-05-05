package com.imax.giraffe

import com.imax.giraffe.presentation.test.StringUtil
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringUtilTest {
    @Test
    fun testReverseString() {
        val input = "Hello, World!"
        val expectedOutput = "!dlroW ,olleH"

        val stringUtil = StringUtil()
        val actualOutput = stringUtil.reverseString(input)

        assertEquals(expectedOutput, actualOutput)
    }
}