package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WinningResultTest {
    @Test
    fun `각 랭크별 당첨된 로또 개수를 정확하게 반환한다`() {
        val lottoMachine = LottoMachine(ManualLottoGenerator(setOf(1, 2, 3, 4, 5, 6)))
        val manualLotto = lottoMachine.generateLotto()
        val autoLotto =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(11),
                    LottoNumber(27),
                    LottoNumber(28),
                ),
            )
        val lottos = Lottos(listOf(manualLotto), listOf(autoLotto))

        val winningNumbers = setOf(1, 2, 3, 11, 22, 33).map { LottoNumber(it) }.toSet()
        val bonusNumber = LottoNumber(7)

        val expectedResult =
            mapOf(
                Rank.FIRST to 0,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 1,
                Rank.FIFTH to 1,
                Rank.MISS to 0,
            )
        val actualResult = WinningResult(lottos).countLottoByRank(winningNumbers, bonusNumber)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `당첨 등수에 맞는 수익률을 반환한다`() {
        val lottoQuantity = LottoQuantity(1000, 1)
        val lottoMachine = LottoMachine(ManualLottoGenerator(setOf(1, 2, 3, 4, 5, 6)))
        val manualLotto = lottoMachine.generateLotto()
        val lottos = Lottos(listOf(manualLotto), listOf())

        val winningNumbers = setOf(1, 2, 3, 11, 22, 33).map { LottoNumber(it) }.toSet()
        val bonusNumber = LottoNumber(7)

        val expectedResult = 5f
        val actualResult = WinningResult(lottos).getProfitRate(winningNumbers, bonusNumber, lottoQuantity)

        assertEquals(expectedResult, actualResult)
    }
}
