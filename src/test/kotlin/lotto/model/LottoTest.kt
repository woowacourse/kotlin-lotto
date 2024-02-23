package lotto.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호들은 중복이 없어야 한다`() {
        val numbers = listOf(1, 2, 3, 3, 4, 5)
        assertThrows<IllegalArgumentException> {
            Lotto(numbers.map { LottoNumber.of(it) })
        }
    }

    @Test
    fun `당첨번호와 일치하는 갯수만큼 리턴한다`() {
        val lotto = Lotto((1..6).map { LottoNumber.of(it) })
        val winningLotto = Lotto((3..8).toList().map { LottoNumber.of(it) })
        val countOfMatch = lotto.calculateCountOfMatch(winningLotto)
        Assertions.assertThat(countOfMatch).isEqualTo(4)
    }

    @Test
    fun `보너스번호가 포함되어 있으면 true를 리턴한다`() {
        val lotto = Lotto((1..6).map { LottoNumber.of(it) })
        val bonusLottoNumber = LottoNumber.of(5)
        val matchBonus = lotto.calculateMatchBonus(bonusLottoNumber)
        Assertions.assertThat(matchBonus).isEqualTo(true)
    }

    @Test
    fun `보너스번호가 포함되어 있지않으면 false를 리턴한다`() {
        val lotto = Lotto((1..6).map { LottoNumber.of(it) })
        val bonusLottoNumber = LottoNumber.of(7)
        val matchBonus = lotto.calculateMatchBonus(bonusLottoNumber)
        Assertions.assertThat(matchBonus).isEqualTo(false)
    }
}
