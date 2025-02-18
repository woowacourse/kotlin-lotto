package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호는 1~45 사이이다`() {
        val lotto = Lotto()
        assertTrue(lotto.numbers.min() >= 1)
        assertTrue(lotto.numbers.max() <= 45)
    }

    @Test
    fun `로또 1매에는 6개의 로또 번호가 존재한다`() {
        val lotto = Lotto()
        assertThat(lotto.numbers.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        val lotto = Lotto()
        assertThat(lotto.numbers.distinct().size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬되어 있다`() {
        val lotto = Lotto()
        assertThat(lotto.numbers.sorted()).isEqualTo(lotto.numbers)
    }
}
