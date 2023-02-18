package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {

    @Test
    fun `보너스번호는 당첨번호와 중복되면 에러를 발생시킨다`() {
        // given
        val lotto = Lotto(
            listOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            ),
        )
        val bonusNumber = LottoNumber.from(3)
        // when
        assertThrows<IllegalArgumentException> {
            WinningLotto(lotto, bonusNumber)
        }
    }
}
