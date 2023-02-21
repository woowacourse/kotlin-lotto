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
        @JvmStatic
        fun provideWrongAmountLottoNumbers() = listOf(
            Arguments.of(
                setOf(
                    LottoNumber(4),
                    LottoNumber(3),
                    LottoNumber(12),
                ),
            ),
            Arguments.of(
                setOf(
                    LottoNumber(4),
                    LottoNumber(3),
                    LottoNumber(12),
                    LottoNumber(17),
                    LottoNumber(21),
                    LottoNumber(35),
                    LottoNumber(41),
                ),
            ),
            Arguments.of(setOf<LottoNumber>()),
        )

        @JvmStatic
        fun provideDuplicateLottoNumbers() = listOf(
            Arguments.of(
                setOf(
                    LottoNumber(4),
                    LottoNumber(3),
                    LottoNumber(12),
                    LottoNumber(11),
                    LottoNumber(11),
                    LottoNumber(12),
                ),
            ),
            Arguments.of(
                setOf(
                    LottoNumber(4),
                    LottoNumber(3),
                    LottoNumber(12),
                    LottoNumber(12),
                    LottoNumber(35),
                    LottoNumber(35),
                ),
            ),
        )
    }
}
