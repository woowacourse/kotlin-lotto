package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `두개의 Lottos를 +연산을 하면 합쳐진 Lottos를 반환한다`() {
        val manualLottoNumber = Lotto(1, 2, 3, 4, 5, 6)
        val autoLottoNumber = Lotto(7, 8, 9, 10, 11, 12)
        val manualLotto = Lottos(listOf(manualLottoNumber))
        val autoLotto = Lottos(listOf(autoLottoNumber))

        val userLottos = manualLotto + autoLotto
        assertThat(userLottos).isEqualTo(Lottos(listOf(manualLottoNumber, autoLottoNumber)))
    }
}
