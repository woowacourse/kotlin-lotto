package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `수동 로또 발행 확인`() {
        val lottos = Lottos()
        val handpickedLottos =
            listOf(
                Lotto(
                    listOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
                Lotto(
                    listOf(
                        LottoNumber(7),
                        LottoNumber(8),
                        LottoNumber(9),
                        LottoNumber(10),
                        LottoNumber(11),
                        LottoNumber(12),
                    ),
                ),
            )
        val numberOfLotto = 5

        lottos.publishLottos(numberOfLotto, handpickedLottos)

        assertThat(lottos.getLottos().containsAll(handpickedLottos)).isTrue
    }

    @Test
    fun `로또 발행수 계산 확인`() {
        // Given
        val lottos = Lottos()
        val purchaseAmount = 1500

        val calculatedNumberOfLotto = lottos.calculateNumberOfLotto(purchaseAmount)

        assertEquals(1, calculatedNumberOfLotto)
    }
}
