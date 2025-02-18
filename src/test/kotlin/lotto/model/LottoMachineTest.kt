package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ValueSource(ints = [-1, 0])
    @ParameterizedTest
    fun `입력한 금액은 0원 이상만 가능하다`(amount: Int) {
        runCatching {
            LottoMachine(amount)
        }.onFailure { error ->
            assert(error.message?.contains("0원 이상의 금액") ?: false)
        }
    }

    @Test
    fun `입력한 금액이 1,000으로 나누어지지 않으면 실패한다`() {
        assertThrows<IllegalArgumentException> { LottoMachine(1001) }
    }

    @Test
    fun `구입 금액만큼 로또 갯수를 반환한다`() {
        val amount = 5000
        val lottoMachine = LottoMachine(amount)
        val lottos = lottoMachine.getLottos()

        Assertions.assertEquals(5, lottos.lottos.size)
    }
}
