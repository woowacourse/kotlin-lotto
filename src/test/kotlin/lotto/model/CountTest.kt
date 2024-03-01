package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CountTest {
    @Test
    fun `0 미만의 수를 시도 횟수로 설정하면, 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Count(-1)
        }
    }

    @Test
    fun `수동 로또 횟수가 전체 로또 횟수를 초과하면, 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Count.ofManual(manualCount = 2, entireCount = 1)
        }
    }

    @Test
    fun `금액을 입력하면, 금액에 맞는 횟수를 반환한다`() {
        val money = Money(2500)
        val expectedCount = 2
        val actualCount = Count.fromMoney(money).value
        assertThat(actualCount).isEqualTo(expectedCount)
    }
}
