package lotto.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class LottoTest {
    @ParameterizedTest
    @ValueSource(strings = ["1, 2, 3, 4, 5, 6, 7, 8", "1, 1, 1, 2, 3, 4", "0, 2, 3, 4, 5, 46"])
    fun `로또 번호는 1~45 사이의 6개의 서로 다른 자연수를 갖지 않으면 오류를 발생시킨다`(input: String) {
        val numbers = input.split(", ").map { it.toInt() }
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1, 2, 3, 4, 5, 6", "40, 41, 42, 43, 44, 45", "7, 2, 3, 4, 5, 35"])
    fun `로또 번호는 1~45 사이의 6개의 서로 다른 자연수를 갖는다`(input: String) {
        val numbers = input.split(", ").map { it.toInt() }
        assertDoesNotThrow { Lotto(numbers) }
    }
}
