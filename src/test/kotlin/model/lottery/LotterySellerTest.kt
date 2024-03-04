package model.lottery

import model.Money
import model.Quantity
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotterySellerTest {
    private val lotterySeller = LotterySeller()

    @Test
    fun `로또 구매 수량을 알려준다`() {
        assertThat(lotterySeller.getLotteryQuantity(Money.wons((3000)))).isEqualTo(Quantity(3))
    }

    @Test
    fun `지불 금액이 1,000 원 단위가 아니면 예외를 던진다`() {
        val payAmount = Money.wons(2400)
        assertThatThrownBy { lotterySeller.getLotteryQuantity(payAmount) }.isExactlyInstanceOf(
            IllegalArgumentException::class.java,
        ).hasMessage("2400를 입력하셨습니다. 1,000 원 단위로 입력하세요.")
    }

    @Test
    fun `100,000원 초과 구매시 예외를 던진다`() {
        val payAmount = Money.wons(500_000)
        assertThatThrownBy { lotterySeller.getLotteryQuantity(payAmount) }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("500000를 입력하셨습니다. 1,000원 이상, 100,000원 이하로만 구매가 가능합니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 3000, 99000])
    fun `1,000원 이상 100,000이하의 금액에 대해서는 정상적으로 동작한다`(amount: Int) {
        assertDoesNotThrow {
            lotterySeller.getLotteryQuantity(Money.wons(amount))
        }
    }
}
