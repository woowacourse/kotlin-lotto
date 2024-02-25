package domain

import domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `보너스 번호가 로또 번호와 중복될 때 예외를 던진다`() {
        val lottoNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber(1)
        val lottery = lottoNumbers.map { LottoNumber(it) }.toSet()

        val exception = assertThrows<IllegalArgumentException> {
            WinningLotto(
                Lotto(lottery),
                bonusNumber
            )
        }

        assertThat(exception.message).isEqualTo("보너스 번호는 당첨번호와 중복되면 안됩니다.")
    }
}
