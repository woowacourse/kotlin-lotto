package domain.money

import domain.game.LottoMachine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 300, 1000, 1200, 3000, 123456])
    fun `0원 이상의 금액이 주어졌을 때, Money 객체 생성시, 예외가 발생하지 않는다`(amount: Int) {
        assertDoesNotThrow {
            Money(amount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1000, -5000, -10])
    fun `0원 미만의 금액이 주어졌을 때, Money 객체 생성시, IllegalArgumentException이 발생한다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            Money(amount)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["10000,10", "50000,50", "100000,100", "1450,1", "0,0"])
    fun `0 이상의 정수가 주어졌을 때, divideBy 호출시, 현재 금액에서 1000으로 나눈 몫을 반환한다`(amount: Int, expected: Int) {
        val money = Money(amount)
        val actual = money.divideBy(Money(LottoMachine.LOTTO_PRICE))

        assertEquals(actual, expected)
    }
}
