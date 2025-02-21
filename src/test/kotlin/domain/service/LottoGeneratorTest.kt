package domain.service

import domain.model.PurchasePrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoGeneratorTest {
    @Test
    fun `구입 금액이 0 이하면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoGeneratorImpl().generate(PurchasePrice(0))
        }.apply { assertThat(this).hasMessage("[ERROR] 천원 이상 입력해주세요.") }
    }

    @Test
    fun `구입 금액이 천원 단위가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LottoGeneratorImpl().generate(PurchasePrice(10101010))
        }.apply { assertThat(this).hasMessage("[ERROR] 천원 단위로 입력해주세요.") }
    }

    @Test
    fun `구입 금액이 2천원이면 로또를 2개 발행한다`() {
        val money = PurchasePrice(2000)
        assertThat(LottoGeneratorImpl().generate(money).size).isEqualTo(2)
    }
}
