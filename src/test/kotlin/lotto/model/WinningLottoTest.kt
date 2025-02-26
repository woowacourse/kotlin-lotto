package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinningLottoTest {
    @Test
    fun `당첨 번호와 보너스 볼은 중복될 수 없다`() {
        val exception = assertThrows<IllegalArgumentException> { WinningLotto(listOf(1, 2, 3, 4, 5, 6), 6) }
        assertThat(exception.message).isEqualTo("[ERROR] 당첨 번호와 보너스 볼은 중복될 수 없습니다.")
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 6개인 경우 1등이 된다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto), Amount(1000))
        assertThat(prizeCalculator.rankCount[Rank.FIRST]).isEqualTo(1)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 5개이고, 보너스 번호가 일치할 경우 2등이 된다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto), Amount(1000))
        assertThat(prizeCalculator.rankCount[Rank.SECOND]).isEqualTo(1)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 5개인 경우 3등이 된다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 5, 9))
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto), Amount(1000))
        assertThat(prizeCalculator.rankCount[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 4개인 경우 4등이 된다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(listOf(1, 2, 3, 4, 8, 9))
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto), Amount(1000))
        assertThat(prizeCalculator.rankCount[Rank.FOURTH]).isEqualTo(1)
    }

    @Test
    fun `당첨 번호와 일치하는 번호가 3개인 경우 5등이 된다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(listOf(1, 2, 3, 7, 8, 9))
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto), Amount(1000))
        assertThat(prizeCalculator.rankCount[Rank.FIFTH]).isEqualTo(1)
    }

    @ParameterizedTest
    @CsvSource(
        "'1, 2, 8, 9, 10, 11'",
        "'1, 8, 9, 10, 11, 12'",
        "'8, 9, 10, 11, 12, 13'",
    )
    fun `당첨 번호와 일치하는 번호가 3개 미만인 경우 탈락된다`(numbers: String) {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        val publishedLotto = Lotto(numbers.split(",").map { it.trim().toInt() })
        val prizeCalculator = PrizeCalculator(winningLotto, listOf(publishedLotto), Amount(1000))
        assertThat(prizeCalculator.rankCount[Rank.MISS]).isEqualTo(1)
    }

    fun WinningLotto(
        numbers: List<Int>,
        bonusNumber: Int,
    ): WinningLotto = WinningLotto(LottoNumbers(numbers.map { number -> LottoNumber(number) }), LottoNumber(bonusNumber))

    fun Lotto(numbers: List<Int>): Lotto = Lotto(LottoNumbers(numbers.map { number -> LottoNumber(number) }))
}
