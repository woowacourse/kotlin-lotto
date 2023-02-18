package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `당첨번호와 보너스 번호가 같으면 exception 발생`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(6)
        assertThrows<IllegalArgumentException> { WinningLotto(lotto, bonusNumber) }
    }

    @Test
    fun `당첨번호와 보너스 번호가 다르면 winningLotto 생성`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(7)
        assertDoesNotThrow { WinningLotto(lotto, bonusNumber) }
    }

    @ParameterizedTest(name = "보너스 번호가 {0}인 경우")
    @ValueSource(ints = [0, 46])
    fun `보너스번호가 범위안에 들어오지 않는 경우 exception 발생`(bonusNumber: Int) {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6).map { number -> LottoNumber(number) }.toSet())
        assertThrows<IllegalArgumentException> {
            WinningLotto(lotto, LottoNumber(bonusNumber))
        }
    }
}
