package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `당첨 번호는 총 6개를 입력해야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(setOf<LottoNumber>()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 4).map { LottoNumber(it) }.toSet()) }
        assertThrows<IllegalArgumentException> { Lotto(setOf(1, 2, 3, 4, 5).map { LottoNumber(it) }.toSet()) }
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
