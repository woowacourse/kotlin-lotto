package lotto.study

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class IntegerTest {
    @ParameterizedTest
    @CsvSource(
        "-129,-129",
        "128,128",
        "-1000,-1000",
    )
    fun `같은 참조값인지 경계 값 확인`(
        number: Integer,
        actual: Integer,
    ) {
        assertFalse(number === actual)
    }

    @ParameterizedTest
    @CsvSource(
        "-129,-129",
        "120,120",
        "-1000,-1000",
    )
    fun `같은 Int는 어떻게 다른지 경계 값 확인`(
        number: Int,
        actual: Int,
    ) {
        assertTrue(number === actual)
    }
}
