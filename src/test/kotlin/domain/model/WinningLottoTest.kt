package domain.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `로또 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        val lotto = Lotto.of(1, 3, 4, 5, 6, 7)
        Assertions
            .assertThatThrownBy {
                WinningLotto(lotto, LottoNumber(6))
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("[ERROR] 보너스 번호는 로또 번호는 중복될 수 없습니다.")
    }
}
