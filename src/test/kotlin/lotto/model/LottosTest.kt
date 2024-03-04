package lotto.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `두개의 Lottos를 +연산을 하면 합쳐진 Lottos를 반환한다`() {
        val lotto1 =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val lotto2 =
            Lotto(
                setOf(
                    LottoNumber(7),
                    LottoNumber(8),
                    LottoNumber(9),
                    LottoNumber(10),
                    LottoNumber(11),
                    LottoNumber(12),
                ),
            )
        val manualLotto = Lottos(listOf(lotto1))
        val autoLotto = Lottos(listOf(lotto2))

        val userLottos = manualLotto + autoLotto
        assertEquals(Lottos(listOf(lotto1, lotto2)), userLottos)
    }
}
