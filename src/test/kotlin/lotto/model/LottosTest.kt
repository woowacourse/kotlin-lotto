package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `두개의 Lottos를 +연산을 하면 합쳐진 Lottos를 반환한다`() {
        val lotto1 =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val lotto2 =
            Lotto(
                setOf(
                    LottoNumber(7),
                    LottoNumber(8),
                    LottoNumber(9),
                    LottoNumber(10),
                    LottoNumber(11),
                    LottoNumber(12),
                ),
            )
        val manualLotto = Lottos(listOf(lotto1))
        val autoLotto = Lottos(listOf(lotto2))

        val userLottos = manualLotto + autoLotto
        assertEquals(Lottos(listOf(lotto1, lotto2)), userLottos)
    }

    @Test
    fun `올바른 우승상황 테스트`() {
        val winningLotto =
            WinningLotto(
                listOf(
                    LottoNumber(11),
                    LottoNumber(15),
                    LottoNumber(17),
                    LottoNumber(21),
                    LottoNumber(22),
                    LottoNumber(35),
                ),
                LottoNumber(8),
            )

        val publishedLottos =
            Lottos(
                listOf(
                    Lotto(
                        setOf(
                            LottoNumber(11),
                            LottoNumber(15),
                            LottoNumber(17),
                            LottoNumber(21),
                            LottoNumber(30),
                            LottoNumber(31),
                        ),
                    ),
                    Lotto(
                        setOf(
                            LottoNumber(11),
                            LottoNumber(15),
                            LottoNumber(17),
                            LottoNumber(21),
                            LottoNumber(22),
                            LottoNumber(40),
                        ),
                    ),
                ),
            )

        val actual = publishedLottos.makeWinningStatics(winningLotto)
        val expected =
            WinningStatistics(
                listOf(
                    WinningStatistic(Rank.FIRST to 0),
                    WinningStatistic(Rank.SECOND to 0),
                    WinningStatistic(Rank.THIRD to 1),
                    WinningStatistic(Rank.FOURTH to 1),
                    WinningStatistic(Rank.FIFTH to 0),
                    WinningStatistic(Rank.MISS to 0),
                ),
            )
        assertThat(actual).isEqualTo(expected)
    }
}
