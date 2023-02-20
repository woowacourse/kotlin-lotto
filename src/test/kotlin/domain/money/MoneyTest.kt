package domain.money

import domain.game.LottoMachine
import org.junit.jupiter.api.Assertions.* // ktlint-disable no-wildcard-imports
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
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

    @ParameterizedTest
    @CsvSource(value = ["10000,5000", "7000,3000", "1,0", "1450,150", "0,0"])
    fun `두 금액이 주어졌을 때, isGreaterThan 호출시 비교 대상 객체의 금액보다 크거나 같으면, True를 반환한다`(givenAmount: Int, otherAmount: Int) {
        assertTrue(Money(givenAmount).isGreaterThan(Money(otherAmount)))
    }

    @ParameterizedTest
    @CsvSource(value = ["5000,10000", "0,3000", "0,1", "1234,4321", "5000,5001"])
    fun `두 금액이 주어졌을 때, isGreaterThan 호출시 비교 대상 객체의 금액보다 작으면, False를 반환한다`(givenAmount: Int, otherAmount: Int) {
        assertFalse(Money(givenAmount).isGreaterThan(Money(otherAmount)))
    }

    @ParameterizedTest
    @MethodSource("provideSubtractTestMoneys")
    fun `금액을 뺄셈할 Money 객체가 주어졌을 때, subtract 호출시, 현재 금액에서 해당 값을 뺀 Money 객체를 반환한다`(
        given: Money,
        other: Money,
        expected: Money,
    ) {
        assertEquals(given - other, expected)
    }

    @Test
    fun `2개의 Money 객체가 주어졌을 때, subtract 호출시 금액이 0보다 작으면, IllegalArgumentException가 발생한다`(given: Money, other: Money) {
        assertThrows<IllegalArgumentException> {
            Money(3000) - Money(5000)
        }
    }

    companion object {
        @JvmStatic
        fun provideSubtractTestMoneys(): List<Arguments> = listOf(
            Arguments.of(Money(10000), Money(5000), Money(5000)),
            Arguments.of(Money(1000), Money(1000), Money(0)),
            Arguments.of(Money(3000), Money(2000), Money(1000))
        )
    }
}
