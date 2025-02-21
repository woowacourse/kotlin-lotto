package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `각 랭크별 당첨된 로또 개수를 정확하게 반환한다`() {
        val lottos =
            listOf(
                Lotto.from(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 7)),
                Lotto.from(listOf(1, 2, 3, 4, 5, 8)),
                Lotto.from(listOf(1, 2, 3, 4, 8, 9)),
                Lotto.from(listOf(1, 2, 3, 8, 9, 10)),
                Lotto.from(listOf(8, 9, 10, 11, 12, 13)),
            )

        val winningNumbers = Lotto.from(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(7)

        val expectedResult =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 1,
                Rank.THIRD to 1,
                Rank.FOURTH to 1,
                Rank.FIFTH to 1,
                Rank.MISS to 1,
            )
        val actualResult = Lottos(lottos).countLottoByRank(winningNumbers, bonusNumber)

        Assertions.assertEquals(expectedResult, actualResult)
    }
}
