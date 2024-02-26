package lotto.util

import lotto.model.LotteryResult
import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.WinningRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningRankTest {
    private val lottoTickets =
        listOf(
            Lotto(
                listOf(
                    LottoNumber("1"),
                    LottoNumber("2"),
                    LottoNumber("3"),
                    LottoNumber("4"),
                    LottoNumber("5"),
                    LottoNumber("6"),
                ),
            ),
            Lotto(
                listOf(
                    LottoNumber("2"),
                    LottoNumber("3"),
                    LottoNumber("4"),
                    LottoNumber("5"),
                    LottoNumber("6"),
                    LottoNumber("7"),
                ),
            ),
            Lotto(
                listOf(
                    LottoNumber("1"),
                    LottoNumber("3"),
                    LottoNumber("5"),
                    LottoNumber("7"),
                    LottoNumber("9"),
                    LottoNumber("11"),
                ),
            ),
        )

    @ParameterizedTest
    @CsvSource("6,False,FIRST", "5,True,SECOND", "5,False,THIRD", "4,True,FOURTH", "3,False,FIFTH", "2,False,MISS")
    fun `당첨 번호 일치 개수에 따른 등수 반환`(
        matchCount: Int,
        matchBonus: Boolean,
        result: WinningRank,
    ) {
        assertThat(WinningRank.convert(matchCount, matchBonus)).isEqualTo(result)
    }

    @ParameterizedTest
    @CsvSource(
        "1 2 3 4 5 6,7,FIRST SECOND FIFTH",
        "2 3 4 5 6 7,45,THIRD FIRST FIFTH",
        "39 40 41 42 43 44,45,MISS MISS MISS",
    )
    fun `발행한 로또와 당첨 번호를 비교하여 각 로또의 등수를 매긴다`(
        winningInput: String,
        bonusNumber: Int,
        resultInput: String,
    ) {
        val lotteryResult =
            LotteryResult(
                Lotto(winningInput.split(" ").map { LottoNumber(it.toInt()) }),
                LottoNumber(bonusNumber),
            )
        val expectedResult = resultInput.split(" ")
        val actualResult =
            lottoTickets.map {
                WinningRank.convert(
                    it.checkWinningNumbers(lotteryResult.winning),
                    it.checkBonusNumbers(lotteryResult.bonusNumber),
                )
            }

        for (idx in actualResult.indices) {
            assertThat(actualResult[idx].name).isEqualTo(expectedResult[idx])
        }
    }
}
