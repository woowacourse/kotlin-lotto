package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoBunchTest {
    @Test
    fun `로또 다발은 101개의 로또를 가질 수 없다`() {
        val lotto = Lotto(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            ),
        )
        assertThrows<IllegalArgumentException> {
            LottoBunch(
                List(101) {
                    lotto
                },
            )
        }
    }
}
