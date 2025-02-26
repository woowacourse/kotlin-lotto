package lotto.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또를 생성할 수 있다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        assertDoesNotThrow { Lotto(numbers) }
    }

    @Test
    fun `로또는 로또 번호의 개수가 6이 아니라면 예외가 발생한다`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(numbers)
            }

        assertThat(exception.message).isEqualTo("로또 번호는 6개여야 하며, 중복될 수 없습니다.")
    }

    @Test
    fun `중복된 번호가 제거된 뒤 로또 번호의 개수가 6이 아니라면 예외가 발생한다`() {
        val numbers = listOf(1, 1, 2, 3, 4, 5)
        val exception =
            assertThrows<IllegalArgumentException> {
                Lotto(numbers)
            }

        assertThat(exception.message).isEqualTo("로또 번호는 6개여야 하며, 중복될 수 없습니다.")
    }

    @Test
    fun `당첨 번호와 몇 개 일치하는지 계산할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = Lotto(listOf(1, 2, 3, 10, 11, 12))

        val expected = 3
        val actual = lotto.calculateMatchLottoNumberCount(winningLotto)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `보너스 번호가 로또에 존재하면 true를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(1)

        val expected = true
        val actual = lotto.isMatchNumber(bonusNumber)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `보너스 번호가 로또에 존재하지 않으면 false를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber.from(9)

        val expected = false
        val actual = lotto.isMatchNumber(bonusNumber)

        assertThat(actual).isEqualTo(expected)
    }
}
