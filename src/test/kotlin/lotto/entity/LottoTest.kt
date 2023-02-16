package lotto.entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5)) }
    }

    @Test
    fun `로또 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { Lotto(listOf(1, 1, 2, 3, 4, 5)) }
    }

    @MethodSource("provideRangeOverLotto")
    @ParameterizedTest
    fun `로또의 번호가 1에서 45 사이 숫자가 아니라면 예외가 발생한다`(lotto: List<Int>) {
        assertThrows<IllegalArgumentException> { Lotto(lotto) }
    }

    companion object {
        @JvmStatic
        fun provideRangeOverLotto(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(0, 1, 2, 3, 4, 5)),
                Arguments.of(listOf(41, 42, 43, 44, 45, 46))
            )
        }
    }
}
