package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class WinningNumbersTest {

    @Test
    fun `당첨 번호와 보너스 번호로 WinningNumbers를 만든다`() {
        // given
        val lotto = Lotto(listOf("1", "2", "3", "4", "5", "6"))
        val bonusNumber = LottoNumber.of(7)

        // when
        val winningNumbers = WinningNumbers(lotto, bonusNumber)

        // then
        assertThat(winningNumbers.lotto).isEqualTo(lotto)
        assertThat(winningNumbers.bonusNumber).isEqualTo(bonusNumber)
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복 되면 에러 발생`() {
        // given
        val lotto = Lotto(listOf("1", "2", "3", "4", "5", "6"))
        val bonusNumber = LottoNumber.of(6)

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { WinningNumbers(lotto, bonusNumber) }
            .withMessageContaining("[Error] 보너스 번호가 당첨 번호와 중복되면 안됩니다.")
    }

    @Test
    fun `당첨 번호를 비교하여 결과를 반환한다`() {
        // given
        val winningNumbers = WinningNumbers(
            Lotto((1..6).map { it.toString() }),
            LottoNumber.of(7)
        )
        val lottoBundle = LottoBundle()
        lottoBundle.autoGenerate(3, TestLottos())

        val expected = mapOf<Rank, Int>(
            Rank.FIRST to 1,
            Rank.SECOND to 1,
            Rank.THIRD to 0,
            Rank.FOURTH to 1,
            Rank.FIFTH to 0,
            Rank.MISS to 0
        )

        // when
        val actual: Map<Rank, Int> = winningNumbers.compareLottoBundle(lottoBundle).rankCount

        // then
        assertThat(actual).isEqualTo(expected)
    }

    inner class TestLottos : LottoGenerator {
        private val firstRankLotto = Lotto((1..6).map { it.toString() })
        private val secondRankLotto = Lotto((2..7).map { it.toString() })
        private val fourthRankLotto = Lotto((3..8).map { it.toString() })

        private val pattern = listOf(firstRankLotto, secondRankLotto, fourthRankLotto)
        private var i = 0

        override fun generate(): Lotto {
            return pattern[i++]
        }
    }
}
