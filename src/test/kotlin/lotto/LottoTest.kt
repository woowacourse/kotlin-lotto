package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가진다`() {
        val nums = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(nums)
        assertThat(lotto.lottoNums.size).isEqualTo(6)
    }

    @Test
    fun `로또 번호는 1~45 숫자 사이에 해당한다`() {
        val nums = listOf(1, 2, 3, 4, 5, 6)
        val lotto = Lotto(nums)
        assertThat(lotto.lottoNums.all { it in 1..45 }).isTrue()
    }

    @Test
    fun `로또 번호는 1~45 숫자 사이에 없을 경우 예외가 발생한다`() {
        val nums = listOf(1, 2, 3, 4, 5, 46)
        assertThrows<IllegalArgumentException> { Lotto(nums) }
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        val nums = listOf(1, 1, 3, 4, 5, 42)
        assertThrows<IllegalArgumentException> { Lotto(nums) }
    }
}
