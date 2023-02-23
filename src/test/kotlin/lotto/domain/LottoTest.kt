package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @MethodSource("provideWrongAmountLottoNumbers")
    @ParameterizedTest
    fun `로또는 LottoNumber 6개로 이루어진 리스트를 가지고있다`(lottoNumbers: Set<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    @MethodSource("provideDuplicateLottoNumbers")
    @ParameterizedTest
    fun `로또 숫자는 중복되면 안된다`(lottoNumbers: Set<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    companion object {
        private fun lottoNumberSetOf(vararg numbers: Int): Set<LottoNumber> =
            numbers.map { LottoNumber(it) }.toSet()

        @JvmStatic
        fun provideWrongAmountLottoNumbers() = listOf(
            Arguments.of(
                lottoNumberSetOf(4, 3, 12),
            ),
            Arguments.of(
                lottoNumberSetOf(4, 3, 12, 17, 21, 35, 41),
            ),
            Arguments.of(setOf<LottoNumber>()),
        )

        @JvmStatic
        fun provideDuplicateLottoNumbers() = listOf(
            Arguments.of(
                lottoNumberSetOf(4, 3, 12, 11, 11, 12),
            ),
            Arguments.of(
                lottoNumberSetOf(4, 3, 12, 12, 35, 35),
            ),
        )
    }
}
