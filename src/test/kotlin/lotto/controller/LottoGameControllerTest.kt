package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.WinningLotto
import lotto.model.WinningRank
import lotto.util.NumberGenerate
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoGameControllerTest {
    private val customNumberGenerate =
        NumberGenerate { idx: Int ->
            LottoNumber.NUMBER_RANGE.toList().subList(idx, idx + Lotto.NUMBER_COUNT)
        }

    @ParameterizedTest
    @CsvSource(
        "1 2 3 4 5 6,7,FIRST SECOND FOURTH",
        "2 3 4 5 6 7,45,THIRD FIRST THIRD",
        "39 40 41 42 43 44,45,MISS MISS MISS",
    )
    fun `발행한 로또와 당첨 번호를 비교하여 각 로또의 등수를 매긴다`(
        winningInput: String,
        bonusNumber: Int,
        resultInput: String,
    ) {
        val lottoMachine = LottoMachine.getMachineWithCounts(3, 0)
        val lottoTickets = lottoMachine.issueAutomaticTickets(3, customNumberGenerate)
        val winningLotto =
            WinningLotto(
                Lotto(winningInput.split(" ").map { LottoNumber(it.toInt()) }),
                LottoNumber(bonusNumber),
            )
        val expectedResult = resultInput.split(" ")
        val actualResult =
            lottoTickets.map {
                WinningRank.convert(
                    it.checkWinningNumbers(winningLotto.winning),
                    it.checkBonusNumbers(winningLotto.bonusNumber),
                )
            }

        for (idx in actualResult.indices)
            Assertions.assertThat(actualResult[idx].name).isEqualTo(expectedResult[idx])
    }
}
