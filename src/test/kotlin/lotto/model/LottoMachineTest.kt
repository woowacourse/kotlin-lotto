package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {
    @Test
    fun `수동 구매 개수와 수동 번호 입력 개수가 일치하지 않으면 에러를 발생한다`() {
        val manualLottoCount = LottoTicketCount(0)
        val manualLottoNumbers = listOf(listOf(1,2,3,4,5,6))
        val autoLottoCount = LottoTicketCount(1)

        assertThrows<IllegalArgumentException> { LottoMachine(manualLottoCount, autoLottoCount, manualLottoNumbers, ) }
    }
}
