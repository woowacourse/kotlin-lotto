package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `두개의 Lottos를 +연산을 하면 합쳐진 Lottos를 반환한다`() {
        val manualLottoNumber = Lotto(1, 2, 3, 4, 5, 6)
        val autoLottoNumber = Lotto(7, 8, 9, 10, 11, 12)
        val manualLotto = Lottos(listOf(manualLottoNumber))
        val autoLotto = Lottos(listOf(autoLottoNumber))

        val userLottos = manualLotto + autoLotto
        assertThat(userLottos).isEqualTo(Lottos(listOf(manualLottoNumber, autoLottoNumber)))
    }

    @Test
    fun `올바른 우승상황 테스트`() {
        val lotto = listOf(11, 15, 17, 21, 22, 35).map { LottoNumber(it) }
        val bonus = LottoNumber(8)
        val winningLotto = WinningLotto(lotto, bonus)

        val firstLotto = Lotto(11, 15, 17, 21, 30, 31)
        val secondLotto = Lotto(11, 15, 17, 21, 22, 40)
        val publishedLottos = Lottos(listOf(firstLotto, secondLotto))

        val actual = publishedLottos.makeWinningStatics(winningLotto)
        val expected =
            WinningStatistics(
                mapOf(
                    Rank.THIRD to 1,
                    Rank.FOURTH to 1,
                ),
            )
        assertThat(actual).isEqualTo(expected)
    }
}
