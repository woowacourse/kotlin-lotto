package lotto.entity

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WinLottoTest {
    @Test
    fun `보너스 번호 1과 당첨번호 1이 중복되면 예외가 발생한다`() {
        // given
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber.from(1)

        // when
        val thrown = assertThrows<IllegalArgumentException> { WinLotto(winNumber, bonus) }
        val except = "보너스 번호와 당첨 번호는 중복될 수 없습니다. 입력된 당첨 번호 : %s, 보너스 번호 : %d".format(
            winNumber.toString(),
            bonus.value
        )

        // then
        assertThat(thrown.message).isEqualTo(except)
    }

    @MethodSource("makeWinStatisticsSource")
    @ParameterizedTest(name = "당첨 번호 : {0}, 결과 : {1}")
    fun `로또 번호를 비교하여 등수를 검사`(winLotto: WinLotto, exceptRank: Rank) {
        // given
        val lottos = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val purchasedLottos = PurchasedLottos(lottos)

        // when
        val rank = winLotto.makeWinStatistics(purchasedLottos).value[0]

        // then
        assertThat(rank).isEqualTo(exceptRank)
    }

    companion object {
        @JvmStatic
        fun makeWinStatisticsSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments(WinLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), LottoNumber.from(45)), Rank.FIRST),
                Arguments.arguments(WinLotto(Lotto(listOf(1, 2, 3, 4, 5, 7)), LottoNumber.from(6)), Rank.SECOND),
                Arguments.arguments(WinLotto(Lotto(listOf(1, 2, 3, 4, 5, 7)), LottoNumber.from(45)), Rank.THIRD),
                Arguments.arguments(WinLotto(Lotto(listOf(1, 2, 3, 4, 7, 8)), LottoNumber.from(45)), Rank.FOURTH),
                Arguments.arguments(WinLotto(Lotto(listOf(1, 2, 3, 7, 8, 9)), LottoNumber.from(45)), Rank.FIFTH),
                Arguments.arguments(WinLotto(Lotto(listOf(1, 2, 7, 8, 9, 10)), LottoNumber.from(45)), Rank.MISS)
            )
        }
    }
}
