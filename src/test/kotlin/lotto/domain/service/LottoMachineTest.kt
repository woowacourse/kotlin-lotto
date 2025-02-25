package lotto.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @Test
    fun `로또 구입 금액을 로또 1장 가격으로 나눈 값이 구입 개수이다`() {
        val count = LottoMachine().calculateTotalCount(12000)
        assertThat(count).isEqualTo(12)
    }

    @Test
    fun `전체 로또 구입 개수에서 수동 구입 개수를 뺀 값이 자동 구입 개수이다`() {
        val autoCount = LottoMachine().calculateAutoCount(12, 3)
        assertThat(autoCount).isEqualTo(9)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 4])
    fun `로또 구입 개수만큼 로또를 발행한다`(count: Int) {
        val lottoTickets = LottoMachine().purchase(count)
        assertEquals(lottoTickets.size, count)
    }
}
