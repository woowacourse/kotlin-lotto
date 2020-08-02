package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LottoTicketsTest {
    @ParameterizedTest
    @MethodSource("createLottoNumbersAndRank")
    internal fun `당첨 번호와 같은 숫자의 개수에 맞는 순위와 당첨 결과`(firstNumber: Int, lastNumber: Int, rank: Rank) {
        val lottoNumbers: List<LottoNumber> = (firstNumber..lastNumber).map { LottoNumber.from(it) }
        val winningNumbers: List<LottoNumber> = (1..6).map { LottoNumber.from(it) }
        val lottoTicket = LottoTicket(lottoNumbers)
        val lottoTickets = LottoTickets(listOf(lottoTicket))
        val winningLotto = WinningLotto(LottoTicket((winningNumbers)))

        val countOfRank = lottoTickets.countOfRank(winningLotto)

        assertAll("당첨 결과 확인",
            {assertThat(countOfRank).hasSize(1)},
            {assertThat(countOfRank[rank]).isEqualTo(1)}
        )
    }

    companion object {
        @JvmStatic
        fun createLottoNumbersAndRank() = listOf(
            Arguments.of(1, 6, Rank.FIRST),
            Arguments.of(2, 7, Rank.SECOND),
            Arguments.of(3, 8, Rank.THIRD),
            Arguments.of(4, 9, Rank.FOURTH),
            Arguments.of(5, 10, Rank.NONE)
        )
    }
}
