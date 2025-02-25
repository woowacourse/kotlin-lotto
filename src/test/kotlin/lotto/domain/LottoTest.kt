package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @ValueSource(ints = [0, 1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `당첨 번호는 총 6개를 입력해야 한다`(size: Int) {
        assertThrows<IllegalArgumentException> { Lotto(List(size) { index: Int -> index + 1 }) }
    }

    @Test
    fun `로또 번호가 중복되면 안 된다`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 1, 2, 3).map { LottoNumber(it) }.toSet()) }
    }

    @Test
    fun `구입금액만큼 로또를 생성한다`() {
        val wantLottoCount = 5
        val wantedNumbers = List(wantLottoCount) { List(Lotto.NUMBERS_SIZE) { index -> index + 1 } }
        val wantPay = wantLottoCount * Lotto.PRICE
        val lottos: List<Lotto> = Lotto.buyLottos(wantPay, wantedNumbers)
        assertThat(lottos.size).isEqualTo(wantLottoCount)
    }
}
