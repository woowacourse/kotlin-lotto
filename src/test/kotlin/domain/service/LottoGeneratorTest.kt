package domain.service

import domain.model.Lotto
import domain.model.PurchasePrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    private lateinit var manualLottoNumbers: List<Lotto>

    @BeforeEach
    fun setup() {
        manualLottoNumbers =
            listOf(
                Lotto.from(1, 3, 4, 5, 6, 7),
                Lotto.from(1, 2, 3, 4, 5, 6),
            )
    }

    @Test
    fun `구입 금액이 3천원이고 수동 로또가 2개면 로또를 3개 발행한다`() {
        val money = PurchasePrice(3000)
        assertThat(LottoGenerator(money).makeLottos(2, manualLottoNumbers).size).isEqualTo(3)
    }

    @Test
    fun `구입 금액이 3천원이고 수동 로또가 2개면 해당 로또에 수동 로또 번호가 포함된다`() {
        val money = PurchasePrice(3000)
        assertTrue(LottoGenerator(money).makeLottos(2, manualLottoNumbers).containsAll(manualLottoNumbers))
    }
}
