package lotto

import lotto.model.LottoMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `중복되지 않는 6개의 숫자를 가진 로또를 발행한다`() {
        val lotto = lottoMachine.createLotto()

        val actual = lotto.numbers.toSet().size

        assertEquals(6, actual)
    }
}
