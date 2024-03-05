package model

import lotto.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {

    @Test
    fun `로또 번호가 5개 일 때 예외를 던지는 지`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5)

        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }

        assertThat(exception.message).isEqualTo("입력된 로또 숫자는 5개입니다. 로또 숫자는 고유한 6개여야 합니다.")
    }

    @Test
    fun `로또 번호가 서로 중복될 때 예외를 던진다`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 5)

        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }

        assertThat(exception.message).isEqualTo("입력된 로또 숫자는 5개입니다. 로또 숫자는 고유한 6개여야 합니다.")
    }

    @Test
    fun `당첨번호와 로또번호가 같을때 당첨된 갯수를 반환하는지`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val targetLotto = Lotto(1, 2, 3, 4, 5, 6)
        val actual = targetLotto.countMatch(lotto)

        val expected = 6

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `자동 로또가 범위내에서 만들어지는지`() {
        val randomLotto = Lotto.makeRandomLotto { (1..6).shuffled() }

        val expected = Lotto(1, 2, 3, 4, 5, 6)

        assertThat(randomLotto).isEqualTo(expected)
    }
}
