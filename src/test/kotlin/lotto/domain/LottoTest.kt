package lotto.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @MethodSource("provideLottoNumbers")
    @ParameterizedTest
    fun `로또는 LottoNumber 6개로 이루어진 리스트를 가지고있다`(lottoNumbers: List<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    companion object {
        @JvmStatic
        fun provideLottoNumbers() = listOf(
            Arguments.of(
                listOf(
                    LottoNumber(4),
                    LottoNumber(3),
                    LottoNumber(12),
                ),
            ),
            Arguments.of(
                listOf(
                    LottoNumber(4),
                    LottoNumber(3),
                    LottoNumber(12),
                    LottoNumber(17),
                    LottoNumber(21),
                    LottoNumber(35),
                    LottoNumber(41),
                ),
            ),
            Arguments.of(listOf<LottoNumber>()),
        )
    }
}
