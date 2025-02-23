package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `각 랭크별 당첨된 로또 개수를 정확하게 반환한다`() {
        val lottos =
            listOf(
                Lotto.from(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 7)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 26)),
                Lotto.from(listOf(1, 2, 3, 4, 25, 26)),
                Lotto.from(listOf(1, 2, 3, 24, 25, 26)),
                Lotto.from(listOf(1, 2, 23, 24, 25, 26)),
            )

        val winningNumbers = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(7)
        val lottoRankDiscriminator = LottoRankDiscriminator(winningNumbers, bonusNumber)

        val expectedResult =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 1,
                Rank.FOURTH to 1,
                Rank.FIFTH to 1,
                Rank.MISS to 1,
            )
        val actualResult = Lottos(lottos).countLottoByRank(lottoRankDiscriminator)

        assertEquals(expectedResult, actualResult)
    }
}
