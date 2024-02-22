package model.lottery

import model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotterySellerTest {
    @Test
    fun `1000원 미만의 거스름돈이 있다면 돌려주어야 한다`() {
        val lotterySeller = LotterySeller(Money.wons(5800))
        assertThat(lotterySeller.exchange())
            .isEqualTo(Money.wons(800))
    }

    @Test
    fun `로또 구매 수량을 알려준다`() {
        val lotterySeller = LotterySeller(Money.wons((3800)))
        assertThat(lotterySeller.getLotteryCount()).isEqualTo(3)
    }

    @Test
    fun `천원 이하의 금액은 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            LotterySeller(Money.wons(500))
        }
    }

    @Test
    fun `100,000원 초과 구매시 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            LotterySeller(Money.wons(500_000))
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 1010, 99000])
    fun `1,000원 이상 100,000이하의 금액에 대해서는 정상적으로 동작한다`(amount: Int) {
        assertDoesNotThrow {
            LotterySeller(Money.wons(amount))
        }
    }
}
