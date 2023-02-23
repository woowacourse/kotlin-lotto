package domain.money

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoneyTest {
    @Test
    fun `0이상의 숫자 값이 주어졌을 때, Money 생성시, 오류가 발생하지 않는다`() {
        assertDoesNotThrow {
            Money(10000)
        }
    }

    @Test
    fun `숫자가 아닌 값이 주어졌을 때, Money 생성시, IllegalArgumentException이 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Money("a".toInt())
        }
    }

    @Test
    fun `0미만의 값이 주어졌을 때, Money 생성시, IllegalArgumentException이 발생한다`() {
        assertThrows<java.lang.IllegalArgumentException> {
            Money(-1000)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["10000,10", "50000,50", "100000,100", "1450,1", "0,0"])
    fun `정수가 주어졌을 때, divideByThousand 호출시, 1000으로 나눈 몫을 반환한다`(money: Int, expected: Int) {
        val actual = Money(money).divideByThousand()

        org.junit.jupiter.api.Assertions.assertEquals(actual, expected)
    }

    @Test
    fun `1000원 짜리 수동 로또의 개수가 주어졌을 때, getChangeByPurchasedLottos() 호출시, 남은 금액을 반환한다`() {
        val money = Money(5000)
        val actual = money.getChangeByPurchasedLottos(3)

        org.junit.jupiter.api.Assertions.assertEquals(2000, actual.value)
    }

    @Test
    fun `구매금액보다 사려는 로또 금액이 더 많을 때 , getChangeByPurchasedLottos() 호출시, IllegalStateException이 발생한다`() {
        assertThrows<IllegalStateException> {
            val money = Money(5000)
            money.getChangeByPurchasedLottos(6)
        }
    }
}