package lotto

import lotto.model.WinningNumbers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `당첨번호와 보너스 번호가 중복된 경우 예외를 발생시킨다`() {
        val winningLottoValue = listOf<String>("1", "2", "3", "4", "5", "6")
        val winningBonusNumberValue = "6"
        assertThrows<IllegalArgumentException> { WinningNumbers.of(winningLottoValue, winningBonusNumberValue) }
    }

    @Test
    fun `당첨번호와 보너스 번호가 중복되지 않은 경우 예외를 발생시키지 않는다`() {
        val winningLottoValue = listOf<String>("1", "2", "3", "4", "5", "6")
        val winningBonusNumberValue = "7"
        assertDoesNotThrow { WinningNumbers.of(winningLottoValue, winningBonusNumberValue) }
    }
}
