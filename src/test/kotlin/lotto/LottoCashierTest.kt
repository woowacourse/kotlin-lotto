package lotto

import lotto.model.LottoCashier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCashierTest {
    @Test
    fun `구입 금액에 따른 총 로또 개수를 계산한다`() {
        val calculator = LottoCashier(14000.0, 0)
        assertThat(calculator.count()).isEqualTo(14)
    }

    @Test
    fun `수동 구입 개수에 따른 자동 로또 개수와 수동 로또 개수를 계산한다`() {
        val calculator = LottoCashier(14000.0, 3)
        assertThat(calculator.autoCount()).isEqualTo(11)
    }

    @Test
    fun `구입 금액이 1000원 미만인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoCashier(800.0, 0) }
    }

    @Test
    fun `구입 개수보다 수동 로또 개수 입력이 더 많을 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoCashier(1000.0, 2) }
    }
}
