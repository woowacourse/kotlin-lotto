package lotto.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `로또 구입 금액을 로또 1장 가격으로 나눈 값이 구입 개수이다`() {
        val count = LottoMachine().calculateTotalCount(12000)
        assertThat(count).isEqualTo(12)
    }
}
