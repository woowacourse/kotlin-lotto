package lotto.domain.model

import lotto.domain.value.LottoNumber
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            val winningNumbers = Lotto.of(1, 2, 3, 4, 5, 6)
            val bonusNumber = LottoNumber.from(45)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val winningNumbers = Lotto.of(1, 2, 3, 4, 5, 6)
            val bonusNumber = LottoNumber.from(1)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `1등 당첨 로또가 1개이면 1등 당첨 횟수는 1이다`() {
        // given
        val winningLotto = WinningLotto(Lotto.of(1, 2, 3, 4, 5, 6), LottoNumber.from(45))
        val firstRankLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val lottos = listOf(firstRankLotto)

        // when
        val lottoResult = winningLotto.getLottoResult(lottos)

        // then
        val actual = lottoResult.winningStats[Rank.FIRST]
        val expected = 1
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `당첨되지 않은 로또가 1개이면 미당첨 횟수는 1이다`() {
        // given
        val winningLotto = WinningLotto(Lotto.of(1, 2, 3, 4, 5, 6), LottoNumber.from(45))
        val firstRankLotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val missRankLotto = Lotto.of(11, 12, 13, 14, 15, 16)
        val lottos = listOf(firstRankLotto, missRankLotto)

        // when
        val lottoResult = winningLotto.getLottoResult(lottos)

        // then
        val actual = lottoResult.winningStats[Rank.MISS]
        val expected = 1
        assertThat(actual).isEqualTo(expected)
    }
}
