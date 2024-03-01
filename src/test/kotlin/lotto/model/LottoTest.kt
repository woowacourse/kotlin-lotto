package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.IllegalArgumentException

class LottoTest {
    @Test
    fun `로또 번호의 수가 6개 미만일 경우, 오류를 발생시킨다`() {
        assertThrows<IllegalArgumentException> { Lotto.of(1, 2, 3, 4, 5) }
    }

    @Test
    fun `로또 번호의 수가 6개를 초과할 경우, 오류를 발생시킨다`() {
        assertThrows<IllegalArgumentException> { Lotto.of(1, 2, 3, 4, 5, 6, 7) }
    }

    @Test
    fun `로또 번호는 중복 번호가 포함될 경우 오류를 발생시킨다`() {
        assertThrows<IllegalArgumentException> { Lotto.of(1, 1, 1, 1, 1, 2) }
    }

    @Test
    fun `로또 번호는 6개의 서로 다른 자연수를 갖는다`() {
        assertDoesNotThrow { Lotto.of(1, 2, 3, 4, 5, 6) }
    }

    @Test
    fun `당첨 번호를 비교하여 동일한 번호의 개수를 반환한다`() {
        val lotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val winningNumbers = Lotto.of(1, 2, 3, 7, 8, 9)
        val result = lotto.countMatchingNumbers(winningNumbers)
        val expected = 3

        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 5, 6])
    fun `로또 번호에 보너스 번호가 포함되어 있으면 True를 반환한다`(bonusNumber: Int) {
        val lotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val result = lotto.hasMatchingBonusNumbers(LottoNumber.valueOf(bonusNumber)!!)
        assertThat(result).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 10, 45])
    fun `로또 번호에 보너스 번호가 포함되어 있지 않으면 False을 반환한다`(bonusNumber: Int) {
        val lotto = Lotto.of(1, 2, 3, 4, 5, 6)
        val result = lotto.hasMatchingBonusNumbers(LottoNumber.valueOf(bonusNumber)!!)
        assertThat(result).isFalse()
    }
}
