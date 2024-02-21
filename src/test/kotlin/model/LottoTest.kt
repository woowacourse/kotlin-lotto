package model

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 갯수가 6개가 아니면 예외 발생`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
        assertThrows<IllegalArgumentException> {
            Lotto.fromList(numbers)
        }
    }

    @Test
    fun `로또 번호에 중복이 있으면 예외 발생`() {
        val numbers = listOf(1, 2, 3, 4, 5, 5)
        assertThrows<IllegalArgumentException> {
            Lotto.fromList(numbers)
        }
    }

    @Test
    fun `로또 번호 일치 갯수 판별`() {
        val result = listOf(1, 2, 3, 4, 5, 6)
        val actual = Lotto.fromList(result)

        val expected = Lotto.fromList(listOf(1, 2, 3, 4, 5, 6))
        assertThat(actual.getCountOfMatch(expected)).isEqualTo(6)
    }

    @Test
    fun `로또 보너스 번호 일치 판별`() {
        val result = listOf(1, 2, 3, 4, 5, 6)
        val actual = Lotto.fromList(result)

        val bonus = Bonus("5", listOf(6, 7, 8, 9, 10, 11))
        actual.hasBonus(bonus) shouldBe true
    }
}
