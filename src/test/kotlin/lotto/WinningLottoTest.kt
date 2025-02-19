package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `보너스 볼은 당첨 번호와 중복되지 않는다`() {
        val bonusNum: String = "7"
        val lottoNums = Lotto(listOf(1, 2, 3, 4, 5, 6))

        assertThrows<IllegalArgumentException> { WinningLotto(lottoNums, bonusNum) }
    }

    @Test
    fun `당첨 번호는 정수 형태로 입력되어야 한다`() {
        val bonusNum: String = "lotto"
        val lottoNums = Lotto(listOf(1, 2, 3, 4, 5, 6))

        assertThrows<IllegalArgumentException> { WinningLotto(lottoNums, bonusNum) }
    }

    @ParameterizedTest
    @EmptySource
    fun ` 보너스 볼 번호는 공백이 불가하다`(num: String) {
        val lottoNums = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThrows<IllegalArgumentException> { WinningLotto(lottoNums, num) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1번", "seven", "-1"])
    fun ` 보너스 볼 번호는 정수 형태로 입력되어야 한다`(input: String) {
        val lottoNums = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThrows<IllegalArgumentException> { WinningLotto(lottoNums, input) }
    }
}
