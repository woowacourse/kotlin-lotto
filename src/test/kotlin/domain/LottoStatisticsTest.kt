package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

class LottoStatisticsTest {
    @Test
    fun `당첨 번호와 4개의 숫자가 일치하는 경우`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 7, 8).map { number -> LottoNumber(number) }.toSet())
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        val method = lottoStatistics.javaClass.declaredMethods.find {
            it.name == "getCountOfMatch"
        }
        method?.isAccessible = true

        // when
        val actual = method?.invoke(lottoStatistics, lotto)

        // then
        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `보너스 번호와 일치하는 경우`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7).map { number -> LottoNumber(number) }.toSet())
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        val method: Method? = lottoStatistics.javaClass.declaredMethods.find {
            it.name == "isBonusNumberMatch"
        }
        method?.isAccessible = true

        // when
        val actual = method?.invoke(lottoStatistics, lotto)

        // then
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `단일 로또를 넘겨 받아서 당첨 결과를 반환한다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet())
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        val method: Method? = lottoStatistics.javaClass.declaredMethods.find {
            it.name == "getRank"
        }
        method?.isAccessible = true

        // when
        val actual = method?.invoke(lottoStatistics, lotto)

        // then
        assertThat(actual).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `Ticket을 넘겨 받아서 당첨 번호와 비교한다`() {
        // given
        val ticket = Ticket(
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()),
                Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()),
                Lotto(listOf(1, 2, 3, 4, 5, 13).map { number -> LottoNumber(number) }.toSet()),
                Lotto(listOf(1, 2, 3, 4, 5, 9).map { number -> LottoNumber(number) }.toSet()),
            )
        )
        val winningNumber = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(winningNumber), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)

        // when
        val result: Map<Rank, Int> = lottoStatistics.compareTicket(ticket)

        // then
        assertThat(result[Rank.FIRST]).isEqualTo(2)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
        assertThat(result[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `총 수익률을 계산한다`() {
        // given
        val winResult = mutableMapOf(
            Pair(Rank.FIRST, 0),
            Pair(Rank.SECOND, 0),
            Pair(Rank.THIRD, 0),
            Pair(Rank.FOURTH, 0),
            Pair(Rank.FIFTH, 1),
            Pair(Rank.MISS, 13)
        )
        val winningNumber = listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet()
        val bonusNumber = LottoNumber(13)
        val winningLotto = WinningLotto(Lotto(winningNumber), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val expected = "0.35"

        // when
        val result = lottoStatistics.calculateProfit(winResult)

        // then
        assertThat(result).isEqualTo(expected)
    }
}
