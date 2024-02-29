package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoGameResultTest {
    private lateinit var lottoGameResult: LottoGameResult

    @BeforeEach
    fun setUp() {
        val bonusNumber = LottoNumber(1)
        val winningLotto = Lotto(2, 3, 4, 5, 6, 7)
        val derivedLotteries = listOf(Lotto(2, 3, 4, 5, 6, 7), Lotto(6, 7, 8, 9, 10, 11))
        lottoGameResult =
            LottoGameResult(
                bonusNumber = bonusNumber,
                winningLotto = winningLotto,
                purchasedLotteries = derivedLotteries,
            )
    }

    @Test
    fun `일치하는 숫자의 수와 보너스 넘버 일치 여부를 통해서 올바른 랭킹 리스트를 반환`() {
        // given : 준비물(객체를 만들기 위한 초기값들. 매개변수)
        val expectedRankResults =
            listOf(
                LottoGameResult.RankResult(Rank.MISS, 1),
                LottoGameResult.RankResult(Rank.FIFTH, 0),
                LottoGameResult.RankResult(Rank.FOURTH, 0),
                LottoGameResult.RankResult(Rank.THIRD, 0),
                LottoGameResult.RankResult(Rank.SECOND, 0),
                LottoGameResult.RankResult(Rank.FIRST, 1),
            )
        // when
        val actualResults = lottoGameResult.results
        // then
        assertThat(actualResults).isEqualTo(expectedRankResults)
    }

    @Test
    fun `로또 게임에 대한 수익률을 계산한다`() {
        // given : 준비물(객체를 만들기 위한 초기값들. 매개변수)
        val expense = Money(2000)
        val expectedEarningRate = 1_000_000.00
        // when
        val actualRate = lottoGameResult.calculateEarningRate(expense)
        // then
        assertThat(actualRate).isEqualTo(expectedEarningRate)
    }
}
