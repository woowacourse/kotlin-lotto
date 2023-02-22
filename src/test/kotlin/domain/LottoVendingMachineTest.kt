package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoVendingMachineTest {
    @Test
    fun `랜덤 로또 생성 성공`() {
        val lottoVendingMachine = LottoVendingMachine()
        assertDoesNotThrow {
            lottoVendingMachine.createRandomLotto()
        }
    }

    @Test
    fun `수동 로또 생성 성공`() {
        val lottoVendingMachine = LottoVendingMachine()
        assertDoesNotThrow {
            lottoVendingMachine.createManualLotto(setOf(1, 2, 3, 4, 5, 6))
        }
    }
}
