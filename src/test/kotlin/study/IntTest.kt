package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IntTest {
    @Test
    fun test1() {
        val actual: Int = 1
        val expected: Int = 1
        assertThat(actual).isEqualTo(expected)
        assertThat(actual).isSameAs(expected)
    }

    @Test
    fun test2() {
        val actual: Int = 1000
        val expected: Int = 1000
        assertThat(actual).isEqualTo(expected)
        assertThat(actual).isNotSameAs(expected)
    }

    @Test
    fun test3() {
        val actual: Int = 1000
        val expected: Int = 1000
        assertThat(actual == expected).isTrue
        assertThat(actual === expected).isTrue
    }

    @Test
    fun test4() {
        val actual: Int? = 1
        val expected: Int? = 1
        assertThat(actual == expected).isTrue
        assertThat(actual === expected).isTrue
    }

    @Test
    fun test5() {
        val actual: Int? = 1000
        val expected: Int? = 1000
        assertThat(actual == expected).isTrue
        assertThat(actual === expected).isFalse
    }

    @Test
    fun test6() {
        val actual: Int? = 128
        val expected: Int? = 128
        assertThat(actual).isNotSameAs(expected)
    }
}
