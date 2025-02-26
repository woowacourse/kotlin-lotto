package domain.service

import domain.model.Lotto
import domain.model.LottoNumber
import domain.model.PurchasePrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    private lateinit var manualLottoNumbers: List<List<LottoNumber>>

    @BeforeEach
    fun setup() {
        manualLottoNumbers =
            listOf(
                Lotto.from(1, 3, 4, 5, 6, 7),
                Lotto.from(1, 2, 3, 4, 5, 6),
            ).map { it.numbers }
    }

    @Test
    fun `구입 금액이 3천원이고 수동 로또가 2개면 로또를 3개 발행한다`() {
        val money = PurchasePrice(3000)
        assertThat(LottoGenerator(money).makeLottos(2, manualLottoNumbers).size).isEqualTo(3)
    }
}
