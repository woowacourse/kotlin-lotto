package lotto.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DrawResultTest {
    @ParameterizedTest
    @CsvSource("7", "1")
    fun `로또 게임에서 보너스 번호는 당첨 번호와 중복되면 안된다`(bonusNumber: String) {
        val winningLotto = Lotto(listOf("1", "2", "3", "4", "5", "7").map { LottoNumber(it) })

        assertThrows<IllegalArgumentException> {
            DrawResult(winningLotto, LottoNumber(bonusNumber))
        }
    }
}
