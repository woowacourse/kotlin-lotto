package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DrawResultTest {
    @ParameterizedTest
    @CsvSource("7", "한글", "46", "-1")
    fun `로또 게임에서 보너스 번호는 당첨 번호와 중복되면 안 되고 1에서부터 45에 자연수로 구성이 된다`(bonusNumber: String) {
        val winningNumbers = Lotto(numbers = listOf("1", "2", "3", "4", "5", "7"))

        assertThrows<IllegalArgumentException> {
            DrawResult(winningNumbers, bonusNumber)
        }
    }
}
