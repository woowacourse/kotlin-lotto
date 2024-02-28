package domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호가 5개 일 때 예외를 던지는 지`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5) }

        assertThat(exception.message).isEqualTo("입력된 로또 숫자는 5개입니다. 로또 숫자는 고유한 6개여야 합니다.")
    }

    @Test
    fun `로또 번호가 서로 중복될 때 예외를 던진다`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5, 5) }

        assertThat(exception.message).isEqualTo("입력된 로또 숫자는 5개입니다. 로또 숫자는 고유한 6개여야 합니다.")
    }

    @Test
    fun `당첨번호와 로또번호가 같을때 일등을 반환하는지`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(7)
        val targetLotto = Lotto(1, 2, 3, 4, 5, 6)
        val expected = targetLotto.findRank(lotto, bonusNumber)
        val actual = Rank.FIRST

        assertThat(expected).isEqualTo(actual)
    }
}
