package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundleDto
import domain.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JudgementTest {
    @Test
    fun `당첨 번호를 비교하여 결과를 반환한다`() {
        // given
        val winningNumbers = WinningNumbers(
            Lotto((1..6).map { LottoNumber.of(it) }.toSet()),
            LottoNumber.of(7),
        )

        val firstRankLottoNumbers = (1..6).map { LottoNumber.of(it) }.toSet()
        val secondRankLottoNumbers = (2..7).map { LottoNumber.of(it) }.toSet()
        val fourthRankLottoNumbers = (3..8).map { LottoNumber.of(it) }.toSet()
        val lottoBundleDto = LottoBundleDto(
            listOf(
                Lotto(firstRankLottoNumbers),
                Lotto(secondRankLottoNumbers),
                Lotto(fourthRankLottoNumbers),
            ),
        )
        val expected = WinningResult(
            listOf(
                ComparingResultDto(6, false),
                ComparingResultDto(5, true),
                ComparingResultDto(4, true),
            ),
        )
        // when
        val actual: WinningResult = Judgement.compareLottoBundle(winningNumbers, lottoBundleDto)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
