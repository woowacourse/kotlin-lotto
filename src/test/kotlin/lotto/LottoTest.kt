package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `로또는 번호 여섯 개를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5))
        }
    }

    @MethodSource("numbers")
    @ParameterizedTest
    fun `로또는 1~45 사이의 숫자를 가져야 한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    @Test
    fun `로또는 중복 되지 않는 숫자를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(4, 3, 1, 2, 2, 2))
        }
    }

    companion object {
        @JvmStatic
        fun numbers(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(0, 1, 2, 3, 4, 5), listOf(41, 42, 43, 44, 45, 46))
            )
        }
    }
}