package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {
    @Test
    fun `당첨번호와 보너스 번호가 같으면 winningLotto 생성 불가`() {
        val bonusNumber = LottoNumber(6)
        assertThrows<IllegalArgumentException> { WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber) }
    }

    @Test
    fun `당첨번호와 보너스 번호가 다르면 winningLotto 생성`() {
        val bonusNumber = LottoNumber(7)
        assertDoesNotThrow { WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber) }
    }

    @ParameterizedTest(name = "보너스 번호가 {0}인 경우")
    @ValueSource(ints = [0, 46])
    fun `보너스번호가 범위안에 들어오지 않는 경우 winningLotto 생성 불가`(bonusNumber: Int) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(Lotto(1, 2, 3, 4, 5, 6), LottoNumber(bonusNumber))
        }
    }

    @Test
    fun `6개가 일치하는지 확인`() {
        // given
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 6), bonusNumber)

        // when
        val matchCount = winningLotto.match(lotto)

        // then
        assertThat(matchCount).isEqualTo(6)
    }

    @Test
    fun `5개가 일치하는지 확인`() {
        // given
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto(Lotto(1, 2, 3, 4, 5, 9), bonusNumber)

        // when
        val matchCount = winningLotto.match(lotto)

        // then
        assertThat(matchCount).isEqualTo(5)
    }
}
