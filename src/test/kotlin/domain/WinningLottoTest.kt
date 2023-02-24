package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WinningLottoTest {

    @Test
    fun `당첨 번호를 생성할 때 보너스 번호가 로또 번호와 중복되면 에러가 발생한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 3
        assertThatIllegalArgumentException()
            .isThrownBy { WinningLotto(numbers, bonusNumber) }
            .withMessage(
                "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : ${Lotto.create(numbers).toList()}, $bonusNumber",
            )
    }

    @Test
    fun `로또 번호, 로또 번호와 중복되지 않는 보너스 번호로 이루어진 당첨 번호를 생성할 수 있다`() {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 10)
        assertThat(winningLotto.lotto).isEqualTo(Lotto.create(1, 2, 3, 4, 5, 6))
        assertThat(winningLotto.bonusNumber).isEqualTo(LottoNumber.valueOf(10))
    }

    @ParameterizedTest
    @MethodSource("getLottoAndRank")
    fun `당첨 번호는 로또에 대한 랭크를 반환할 수 있다`(lotto: Lotto, expect: Rank) {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        val result = winningLotto.getRankOf(lotto)

        assertThat(result).isEqualTo(expect)
    }

    private fun getLottoAndRank() = listOf(
        Arguments.of(Lotto.create(1, 2, 3, 4, 5, 6), Rank.FIRST),
        Arguments.of(Lotto.create(2, 3, 4, 5, 6, 7), Rank.SECOND),
        Arguments.of(Lotto.create(2, 3, 4, 5, 6, 8), Rank.THIRD),
        Arguments.of(Lotto.create(3, 4, 5, 6, 7, 8), Rank.FOURTH),
        Arguments.of(Lotto.create(4, 5, 6, 7, 8, 9), Rank.FIFTH),
        Arguments.of(Lotto.create(5, 6, 7, 8, 9, 10), Rank.MISS),
    )
}
