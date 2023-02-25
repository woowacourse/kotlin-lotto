import domain.* // ktlint-disable no-wildcard-imports
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("produceOverlayBonusNumber")
    fun `보너스 번호가 당첨번호와 중복이 있을 경우 예외가 발생한다`(winningLotto: Lotto, bonus: LottoNumber) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(winningLotto, bonus)
        }
    }

    @CsvSource(
        value = ["1,2,3,7,8,9:20:FIFTH", "1,2,3,4,8,9:20:FOURTH", "1,2,3,4,5,9:20:THIRD", "1,2,3,4,5,9:6:SECOND", "1,2,3,4,5,6:20:FIRST"],
        delimiter = ':',
    )
    @ParameterizedTest
    fun `생성된 로또가 당첨 로또와 보너스 번호를 비교해서 등수 확인`(lottoNumbers: String, bonus: Int, rank: String) {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val winningLotto = Lotto(*lottoNumbers.split(",").map { it.toInt() }.toIntArray())
        val bonusNumber = LottoNumber.from(bonus)

        val actual = WinningLotto(winningLotto, bonusNumber).matchLotto(lotto)
        assertThat(actual).isEqualTo(Rank.valueOf(rank))
    }

    @Test
    fun `당첨된 결과를 확인`() {
        val lottos = Lottos(
            intArrayOf(1, 2, 3, 4, 5, 6),
            intArrayOf(3, 4, 5, 6, 7, 8),
            intArrayOf(3, 4, 5, 10, 11, 12),
        )
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(20))
        val actual = WinningResult(
            mapOf(
                Rank.FIRST to 1,
                Rank.FOURTH to 1,
                Rank.FIFTH to 1,
            ),
        )
        assertThat(winningLotto.matchLottos(lottos)).isEqualTo(actual)
    }

    companion object {
        @JvmStatic
        fun produceOverlayBonusNumber(): List<Arguments> {
            return listOf(
                Arguments.of(Lotto(1, 2, 3, 4, 5, 6), LottoNumber.from(6)),
                Arguments.of(Lotto(1, 5, 10, 15, 20, 30), LottoNumber.from(15)),
            )
        }
    }
}
