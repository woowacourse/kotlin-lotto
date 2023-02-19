package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @MethodSource("provideWrongAmountLottoNumbers")
    @ParameterizedTest
    fun `로또는 LottoNumber 6개로 이루어진 리스트를 가지고있다`(lottoNumbers: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    @MethodSource("provideDuplicateLottoNumbers")
    @ParameterizedTest
    fun `로또 숫자는 중복되면 안된다`(lottoNumbers: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    companion object {
        @JvmStatic
        fun provideWrongAmountLottoNumbers() = listOf(
            Arguments.of(
                listOf(
                    LottoNumber.from(4),
                    LottoNumber.from(3),
                    LottoNumber.from(12),
                ),
            ),
            Arguments.of(
                listOf(
                    LottoNumber.from(4),
                    LottoNumber.from(3),
                    LottoNumber.from(12),
                    LottoNumber.from(17),
                    LottoNumber.from(21),
                    LottoNumber.from(35),
                    LottoNumber.from(41),
                ),
            ),
            Arguments.of(listOf<LottoNumber>()),
        )

        @JvmStatic
        fun provideDuplicateLottoNumbers() = listOf(
            Arguments.of(
                listOf(
                    LottoNumber.from(4),
                    LottoNumber.from(3),
                    LottoNumber.from(12),
                    LottoNumber.from(11),
                    LottoNumber.from(11),
                    LottoNumber.from(12),
                ),
            ),
            Arguments.of(
                listOf(
                    LottoNumber.from(4),
                    LottoNumber.from(3),
                    LottoNumber.from(12),
                    LottoNumber.from(12),
                    LottoNumber.from(35),
                    LottoNumber.from(35),
                ),
            ),
        )
    }
}
