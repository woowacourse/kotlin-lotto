package lotto.domain

import lotto.domain.Lotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `로또는 번호 여섯 개를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(TestNumberGenerator(listOf(1, 2, 3, 4, 5)).generate())
        }
    }

    @MethodSource("numbers")
    @ParameterizedTest
    fun `로또는 1~45 사이의 숫자를 가져야 한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(TestNumberGenerator(numbers).generate())
        }
    }

    @Test
    fun `로또는 중복 되지 않는 숫자를 가져야 한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(TestNumberGenerator(listOf(4, 3, 1, 2, 2, 2)).generate())
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

    class TestNumberGenerator(private val numbers: List<Int>) : LottoNumberGenerator {
        override fun generate(): List<Int> {
            return numbers
        }

    }
}