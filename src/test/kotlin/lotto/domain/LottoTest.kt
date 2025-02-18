package lotto.domain

import lotto.RandomGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호는 1~45 사이이다`() {
        val number: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(number)
        assertThat(lotto.numbers.all { it in 1..45 }).isTrue()
    }

    @Test
    fun `로또 번호는 중복되지 않는 6개의 숫자를 갖는다`() {
        val randomGenerator = RandomGenerator()
        val lotto = Lotto(randomGenerator.getRandomNumberList())
        assertThat(lotto.numbers.size).isEqualTo(6)
    }
}
