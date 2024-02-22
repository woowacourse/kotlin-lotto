package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `보너스 번호가 로또 번호와 중복될 때 예외를 던진다`() {
        val exception = assertThrows<IllegalArgumentException> {
            WinningLotto(
                Lotto(List(6) { LottoNumber(it + 1) }),
                LottoNumber(1)
            )
        }
        Assertions.assertThat(exception.message).isEqualTo(WinningLotto.DUPLICATED_BONUS_NUMBER_EXCEPTION_MESSAGE)
    }
}
