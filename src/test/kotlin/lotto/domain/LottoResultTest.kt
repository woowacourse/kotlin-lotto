package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LottoResultTest {
    @ParameterizedTest
    @MethodSource("createRangeAndResult")
    internal fun `결과 생성`(
        firstNumber: Int,
        lastNumber: Int,
        none: Int,
        fourth: Int,
        third: Int,
        second: Int,
        first: Int
    ) {
        val lottoNumbers: List<LottoNumber> = (firstNumber..lastNumber).map { LottoNumber.from(it) }
        val winningNumbers: List<LottoNumber> = (1..6).map { LottoNumber.from(it) }
        val lottoTicket = LottoTicket(lottoNumbers)
        val lottoTickets = LottoTickets(listOf(lottoTicket))
        val winningLotto = WinningLotto(LottoTicket((winningNumbers)))

        val lottoResult = LottoResult.of(lottoTickets, winningLotto)

        assertAll("결과 생성 확인",
            {
                assertThat(lottoResult.result.keys).isEqualTo(
                    listOf(
                        Rank.NONE,
                        Rank.FOURTH,
                        Rank.THIRD,
                        Rank.SECOND,
                        Rank.FIRST
                    )
                )
            },
            { assertThat(lottoResult.result[Rank.NONE]).isEqualTo(none) },
            { assertThat(lottoResult.result[Rank.FOURTH]).isEqualTo(fourth) },
            { assertThat(lottoResult.result[Rank.THIRD]).isEqualTo(third) },
            { assertThat(lottoResult.result[Rank.SECOND]).isEqualTo(second) },
            { assertThat(lottoResult.result[Rank.FIRST]).isEqualTo(first) }
        )
    }

    companion object {
        @JvmStatic
        fun createRangeAndResult() = listOf(
            Arguments.of(5, 10, 1, 0, 0, 0, 0),
            Arguments.of(4, 9, 0, 1, 0, 0, 0),
            Arguments.of(3, 8, 0, 0, 1, 0, 0),
            Arguments.of(2, 7, 0, 0, 0, 1, 0),
            Arguments.of(1, 6, 0, 0, 0, 0, 1)
        )
    }
}
