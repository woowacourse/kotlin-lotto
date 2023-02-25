package lotto.entity

import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WinLottoTest {
    @Test
    fun `보너스 번호 1과 당첨번호 1이 중복되면 예외가 발생한다`() {
        // given
        val winNumber = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = LottoNumber.from(1)

        // when
        val thrown = assertThrows<IllegalArgumentException> { WinLotto(winNumber, bonus) }
        val except = "보너스 번호와 당첨 번호는 중복될 수 없습니다. 입력된 당첨 번호 : %s, 보너스 번호 : %d".format(
            winNumber.toString(), bonus.value
        )

        // then
        assertThat(thrown.message).isEqualTo(except)
    }

    @ParameterizedTest(name = "당첨 번호 : {0}, 결과 : {1}")
    @CsvSource(
        value = ["1,2,3,4,5,6:45:0", "1,2,3,4,5,7:6:1", "1,2,3,4,5,7:45:2", "1,2,3,4,7,8:45:3", "1,2,3,7,8,9:45:4", "1,2,7,8,9,10:45:5"],
        delimiter = ':'
    )
    fun `로또 번호를 비교하여 등수를 검사`(winNumber: String, bonus: Int, exceptRank: Int) {
        // given
        val splittedWinNumber = winNumber.split(',')
        val instantiatedWinNumber = Lotto(splittedWinNumber.map { it.toInt() })
        val instantiatedBonus = LottoNumber.from(bonus)
        val winLotto = WinLotto(instantiatedWinNumber, instantiatedBonus)
        val lottos = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val purchasedLottos = PurchasedLottos(lottos)
        val except = mapOf(Rank.values()[exceptRank] to 1)

        // when
        val rank = winLotto.makeWinStatistics(purchasedLottos).value

        // then
        assertThat(rank).isEqualTo(except)
    }
}
